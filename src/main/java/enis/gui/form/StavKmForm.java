package enis.gui.form;

import org.springframework.context.annotation.Scope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import enis.backend.model.StavKm;
import enis.backend.utils.IForm;
import enis.backend.utils.LongConverter;
import enis.gui.designs.StavKmFormDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class StavKmForm extends StavKmFormDesign implements IForm {

	private static final long serialVersionUID = 2711538846089449606L;

	private StavKm stavKm;
	
	private BeanValidationBinder<StavKm> binder;

	public StavKmForm() {
		super();
		binder = new BeanValidationBinder<>(StavKm.class);
		stavKm = new StavKm();
		bindFields();
	}
	
	private void bindFields() {
		binder.setBean(stavKm);
		
		binder.forField(najetKmTF).withConverter(new LongConverter()).bind(StavKm::getNajetKm, StavKm::setNajetKm);
		binder.forField(zbyvajiciKmTF).withConverter(new LongConverter()).bind(StavKm::getZbyvajiciKm, StavKm::setZbyvajiciKm);
	}

	public StavKm getStavKmFromBinder() {
		return binder.getBean();
	}

	public void setStavKm(StavKm stavKm) {
		this.stavKm = stavKm;
		binder.setBean(stavKm);
	}
	
	@Override
	public boolean validateForm() {
		return binder.validate().isOk();
	}

	@Override
	public void setReadOnly() {
		najetKmTF.setReadOnly(true);
		zbyvajiciKmTF.setReadOnly(true);
	}
}
