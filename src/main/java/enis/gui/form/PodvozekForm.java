package enis.gui.form;

import org.springframework.context.annotation.Scope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import enis.backend.model.Podvozek;
import enis.backend.utils.IForm;
import enis.backend.utils.LongConverter;
import enis.gui.designs.PodvozekFormDesign;

@SpringComponent
@UIScope
@Scope("prototype")
public class PodvozekForm extends PodvozekFormDesign implements IForm {

	private static final long serialVersionUID = -3714596027398094363L;

	private Podvozek podvozek;
	
	private BeanValidationBinder<Podvozek> binder;

	public PodvozekForm() {
		super();
		binder = new BeanValidationBinder<>(Podvozek.class);
		podvozek = new Podvozek();
		bindFields();
	}
	
	private void bindFields() {
		binder.setBean(podvozek);	
		
		binder.forField(vzdalOtocCepuPodvozkuTF).withConverter(new LongConverter()).bind(Podvozek::getVzdalOtocCepuPodvozku, Podvozek::setVzdalOtocCepuPodvozku);
		binder.forField(rozvorPodvozkuTF).withConverter(new LongConverter()).bind(Podvozek::getRozvorPodvozku, Podvozek::setRozvorPodvozku);
		binder.forField(unosnostKonstrukcniTF).withConverter(new LongConverter()).bind(Podvozek::getUnosnostKonstrukcni, Podvozek::setUnosnostKonstrukcni);
		binder.forField(pocetPodvozuTF).withConverter(new LongConverter()).bind(Podvozek::getPocetPodvozu, Podvozek::setPocetPodvozu);
		binder.forField(pocetClankuTF).withConverter(new LongConverter()).bind(Podvozek::getPocetClanku, Podvozek::setPocetClanku);
		binder.forField(pocetNapravTF).withConverter(new LongConverter()).bind(Podvozek::getPocetNaprav, Podvozek::setPocetNaprav);
	}

	public Podvozek getPodvozekFromBinder() {
		return binder.getBean();
	}

	public void setPodvozek(Podvozek podvozek) {
		this.podvozek = podvozek;
		binder.setBean(podvozek);
	}

	@Override
	public boolean validateForm() {
		return binder.validate().isOk();
	}

	@Override
	public void setReadOnly() {
		vzdalOtocCepuPodvozkuTF.setReadOnly(true);
		rozvorPodvozkuTF.setReadOnly(true);
		unosnostKonstrukcniTF.setReadOnly(true);
		pocetPodvozuTF.setReadOnly(true);
		pocetClankuTF.setReadOnly(true);
		pocetNapravTF.setReadOnly(true);
	}

}
