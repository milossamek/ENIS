package enis.gui.form;

import org.springframework.context.annotation.Scope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import enis.backend.model.LozneRozmery;
import enis.backend.model.Rozmery;
import enis.backend.utils.IForm;
import enis.backend.utils.LongConverter;
import enis.gui.designs.RozmeryFormDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class RozmeryForm extends RozmeryFormDesign implements IForm {

	private static final long serialVersionUID = -2355432770524572056L;

	private Rozmery rozmery;	
	private LozneRozmery lozneRozmery;
	
	private BeanValidationBinder<Rozmery> binderRozmery;
	private BeanValidationBinder<LozneRozmery> binderLozneRozmery;

	public RozmeryForm() {
		super();
		binderRozmery = new BeanValidationBinder<>(Rozmery.class);
		binderLozneRozmery = new BeanValidationBinder<>(LozneRozmery.class);
		rozmery = new Rozmery();
		lozneRozmery = new LozneRozmery();
		bindFields();
	}

	private void bindFields() {
		binderRozmery.setBean(rozmery);		
		binderLozneRozmery.setBean(lozneRozmery);
		
		binderRozmery.forField(delkaPresNaraznikyTF).withConverter(new LongConverter()).bind(Rozmery::getDelkaPresNarazniky, Rozmery::setDelkaPresNarazniky);
		
		binderLozneRozmery.forField(loznaDelkaTF).withConverter(new LongConverter()).bind(LozneRozmery::getLoznaDelka, LozneRozmery::setLoznaDelka);
		binderLozneRozmery.forField(loznaPlochaTF).withConverter(new LongConverter()).bind(LozneRozmery::getLoznaPlocha, LozneRozmery::setLoznaPlocha);
		binderLozneRozmery.forField(loznyProstorTF).withConverter(new LongConverter()).bind(LozneRozmery::getLoznyProstor, LozneRozmery::setLoznyProstor);
		binderLozneRozmery.forField(vyskaLoznePlochyVozuTF).withConverter(new LongConverter()).bind(LozneRozmery::getVyskaLoznePlochyPrVozu, LozneRozmery::setVyskaLoznePlochyPrVozu);		
	}
	
	public Rozmery getRozmeryFromBinder() {
		return binderRozmery.getBean();
	}

	public void setRozmery(Rozmery rozmery) {
		this.rozmery = rozmery;
		this.lozneRozmery = rozmery.getLozneRozmery();
		binderRozmery.setBean(rozmery);
		binderLozneRozmery.setBean(lozneRozmery);
	}
	
	public LozneRozmery getLozneRozmeryFromBinder() {
		return binderLozneRozmery.getBean();
	}
	
	@Override
	public boolean validateForm() {	
		boolean validRozmery = binderRozmery.validate().isOk();
		boolean validLozneRozmery = binderLozneRozmery.validate().isOk();
		return validRozmery && validLozneRozmery;
	}

	@Override
	public void setReadOnly() {
		delkaPresNaraznikyTF.setReadOnly(true);
		loznaDelkaTF.setReadOnly(true);
		loznaPlochaTF.setReadOnly(true);
		loznyProstorTF.setReadOnly(true);
		vyskaLoznePlochyVozuTF.setReadOnly(true);
	}
}
