package enis.gui.form;

import org.springframework.context.annotation.Scope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import enis.backend.model.RucniBrzda;
import enis.backend.service.CiselnikRucniBrzdaService;
import enis.backend.utils.IForm;
import enis.backend.utils.LongConverter;
import enis.backend.utils.RucniConverter;
import enis.gui.designs.RucniBrzdaFormDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class RucniBrzdaForm extends RucniBrzdaFormDesign implements IForm {
	private CiselnikRucniBrzdaService rucniService;

	private static final long serialVersionUID = -4463103239804207999L;

	private RucniBrzda rucniBrzda;
	
	private BeanValidationBinder<RucniBrzda> binder;


	public RucniBrzdaForm() {
		super();
		binder = new BeanValidationBinder<>(RucniBrzda.class);
		rucniBrzda = new RucniBrzda();
	}
	
	private void bindFields() {
		binder.setBean(rucniBrzda);
		binder.forField(comboDruhRucniBrzdy).withConverter(new RucniConverter(rucniService)).bind(RucniBrzda::getDruhRucniBrzdy,RucniBrzda::setDruhRucniBrzdy);
		binder.forField(brzdiciHmotnostRucniBrzdyTF).withConverter(new LongConverter()).bind(RucniBrzda::getBrzdiciHmotnostRucniBrzdy, RucniBrzda::setBrzdiciHmotnostRucniBrzdy);
	}

	public void setBrzdy(CiselnikRucniBrzdaService service) {
		this.rucniService=service;
		comboDruhRucniBrzdy.setItems(service.findAll());
		bindFields();
	}

	public RucniBrzda getRucniBrzdaFromBinder() {
		return binder.getBean();
	}

	public void setRucniBrzda(RucniBrzda rucniBrzda) {
		this.rucniBrzda = rucniBrzda;
		binder.setBean(rucniBrzda);
	}
	
	@Override
	public boolean validateForm() {
		return binder.validate().isOk();
	}

	@Override
	public void setReadOnly() {
		comboDruhRucniBrzdy.setReadOnly(true);
		brzdiciHmotnostRucniBrzdyTF.setReadOnly(true);
	}
}
