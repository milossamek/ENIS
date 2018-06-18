package enis.gui.form;

import org.springframework.context.annotation.Scope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import enis.backend.model.VzduchovaBrzda;
import enis.backend.service.CiselnikVzduchovaBrzdaService;
import enis.backend.utils.IForm;
import enis.backend.utils.LongConverter;
import enis.backend.utils.VzduchConverter;
import enis.gui.designs.VzduchovaBrzdaFormDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class VzduchovaBrzdaForm extends VzduchovaBrzdaFormDesign implements IForm {

	private static final long serialVersionUID = 5722578145796143882L;

	private CiselnikVzduchovaBrzdaService vzduchService;

	private VzduchovaBrzda vzduchovaBrzda;
	
	private BeanValidationBinder<VzduchovaBrzda> binder;

	public VzduchovaBrzdaForm() {
		super();
		binder = new BeanValidationBinder<>(VzduchovaBrzda.class);
		vzduchovaBrzda = new VzduchovaBrzda();
	}
	
	private void bindFields() {
		binder.setBean(vzduchovaBrzda);
		binder.forField(comboDruhVzduch).withConverter(new VzduchConverter(vzduchService)).bind(VzduchovaBrzda::getDruhVzduchoveBrzdy,VzduchovaBrzda::setDruhVzduchoveBrzdy);
		binder.forField(brzdiciHmotnostVzduchBrzdyTF).withConverter(new LongConverter()).bind(VzduchovaBrzda::getBrzdiciHmotnostVzduchBrzdy, VzduchovaBrzda::setBrzdiciHmotnostVzduchBrzdy);
	}

	public void setBrzdy(CiselnikVzduchovaBrzdaService service) {
		this.vzduchService=service;
		comboDruhVzduch.setItems(service.findAll());
		bindFields();
	}

	public VzduchovaBrzda getVzduchovaBrzdaFromBinder() {
		return binder.getBean();
	}

	public void setVzduchovaBrzda(VzduchovaBrzda vzduchovaBrzda) {
		this.vzduchovaBrzda = vzduchovaBrzda;
		binder.setBean(vzduchovaBrzda);
	}
	
	@Override
	public boolean validateForm() {
		return binder.validate().isOk();
	}

	@Override
	public void setReadOnly() {
		comboDruhVzduch.setReadOnly(true);
		brzdiciHmotnostVzduchBrzdyTF.setReadOnly(true);
	}
}
