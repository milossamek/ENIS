package enis.gui.form;

import java.util.Arrays;

import org.springframework.context.annotation.Scope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import enis.backend.model.StavVozu;
import enis.backend.model.Vuz;
import enis.backend.utils.IForm;
import enis.backend.utils.LongConverter;
import enis.gui.designs.VuzFormDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class VuzForm extends VuzFormDesign implements IForm {

	private static final long serialVersionUID = -971776566985491176L;

	private Vuz vuz;
	
	private BeanValidationBinder<Vuz> binder;
	
	public VuzForm() {
		super();
		binder = new BeanValidationBinder<>(Vuz.class);
		vuz = new Vuz();		
		setupPage();		
		bindFields();
	}
	
	private void setupPage() {
		vuzCisTF.setId("vuzCis");
		
		stavVozuCB.setEmptySelectionAllowed(false);
		stavVozuCB.setDataProvider(DataProvider.ofCollection(Arrays.asList(StavVozu.values())));
		stavVozuCB.setItemCaptionGenerator(stav -> stav.getName());
	}

	private void bindFields() {
		binder.setBean(vuz);
		
		binder.forField(vuzCisTF).withConverter(new LongConverter()).bind(Vuz::getVuzCis, Vuz::setVuzCis);
		binder.forField(vlastniHmotnostTF).withConverter(new LongConverter()).bind(Vuz::getVlastniHmotnost, Vuz::setVlastniHmotnost);
		binder.forField(maxRychlostTF).withConverter(new LongConverter()).bind(Vuz::getMaxRychlost, Vuz::setMaxRychlost);

		binder.bind(stavVozuCB, "stavVozu");
	}

	public Vuz getVuzFromBinder() {
		return binder.getBean();
	}

	public void setVuz(Vuz vuz) {
		this.vuz = vuz;
		binder.setBean(vuz);
	}
	
	@Override
	public boolean validateForm() {
		return binder.validate().isOk();
	}

	@Override
	public void setReadOnly() {
		vuzCisTF.setReadOnly(true);
		vlastniHmotnostTF.setReadOnly(true);
		maxRychlostTF.setReadOnly(true);
		stavVozuCB.setReadOnly(true);
	}
}
