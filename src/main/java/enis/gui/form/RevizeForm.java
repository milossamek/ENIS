package enis.gui.form;

import org.springframework.context.annotation.Scope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import enis.backend.model.Revize;
import enis.backend.utils.IForm;
import enis.backend.utils.LongConverter;
import enis.gui.designs.RevizeFormDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class RevizeForm extends RevizeFormDesign implements IForm {

	private static final long serialVersionUID = 1404451480191989001L;

	private Revize revize;
	
	private BeanValidationBinder<Revize> binder;
	
	public RevizeForm() {
		super();
		binder = new BeanValidationBinder<>(Revize.class);
		revize = new Revize();
		bindFields();
	}
	
	private void bindFields() {
		binder.setBean(revize);
		
		binder.forField(revizLhutaTF).withConverter(new LongConverter()).bind(Revize::getRevizLhuta, Revize::setRevizLhuta);
		
		binder.bind(datumPosRevizeDF, "datumPosRevize");
	}

	public Revize getRevizeFromBinder() {
		return binder.getBean();
	}

	public void setRevize(Revize revize) {
		this.revize = revize;
		binder.setBean(revize);
	}
	
	@Override
	public boolean validateForm() {
		return binder.validate().isOk();
	}

	@Override
	public void setReadOnly() {
		revizLhutaTF.setReadOnly(true);
		datumPosRevizeDF.setReadOnly(true);		
	}
}
