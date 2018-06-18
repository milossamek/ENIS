package enis.gui.form;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.changelog.SimpleTextChangeLog;
import org.javers.core.diff.Change;
import org.javers.hibernate.integration.HibernateUnproxyObjectAccessHook;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import enis.backend.model.Historie;
import enis.backend.model.Podvozek;
import enis.backend.model.Revize;
import enis.backend.model.Rozmery;
import enis.backend.model.RucniBrzda;
import enis.backend.model.StavKm;
import enis.backend.model.Vuz;
import enis.backend.model.VzduchovaBrzda;
import enis.backend.service.CiselnikRucniBrzdaService;
import enis.backend.service.CiselnikVzduchovaBrzdaService;
import enis.backend.service.HistorieService;
import enis.backend.service.UzivatelService;
import enis.backend.service.VuzService;
import enis.backend.utils.OperationType;
import enis.gui.designs.MainWinFormDesign;
import enis.security.utils.SecurityUtils;

@SpringComponent
@UIScope
@Scope("prototype")
public class MainWinForm extends MainWinFormDesign {

	private static final long serialVersionUID = -9105872329437796369L;

	private OperationType type;
	
	@Autowired
	private VuzForm vuzForm;
	@Autowired
	private StavKmForm stavKmForm;
	@Autowired
	private PodvozekForm podvozekForm;
	@Autowired
	private RevizeForm revizeForm;
	@Autowired
	private RozmeryForm rozmeryForm;
	@Autowired
	private RucniBrzdaForm rucniBrzdaForm;
	@Autowired
	private VzduchovaBrzdaForm vzduchovaBrzdaForm;
	
	@Autowired
	private VuzService vuzService;

	@Autowired
	private HistorieService historie;

	@Autowired
	private UzivatelService uzivatel;

	@Autowired
	private CiselnikRucniBrzdaService rucniService;

	@Autowired
	private CiselnikVzduchovaBrzdaService vzduchService;

	private Runnable closeFunction;

	private Javers javers;
	
	public MainWinForm() {
		super();
		addListeners();
		javers = JaversBuilder.javers().withObjectAccessHook( new HibernateUnproxyObjectAccessHook()).build();
	}
	
	private void addListeners() {
		confirmButton.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 5404705644604260557L;

			@Override
			public void buttonClick(ClickEvent event) {
				Vuz vuz = getVuzDataFromForms();
				if(vuz != null){
					commitVuz(vuz);

					String test = getVuzChanges(vuz);
					System.out.println(test);
					String loggedUser = SecurityUtils.getLoggedUser();
					historie.save(new Historie(uzivatel.findUserByUsername(loggedUser),type.getName() + " " + vuz.getVuzCis(), new Date(),test));
					vuzService.saveVuz(vuz);
					closeFunction.run();
				}
			}
		});
	}

	private void commitVuz(Vuz vuz) {
		javers.commit("vuz",vuz);
		javers.commit("stavKM",vuz.getStavKm());
		javers.commit("rozmer",vuz.getRozmer());
		javers.commit("vzduchova brzda",vuz.getVzduchovaBrzda());
		javers.commit("rucni brzda",vuz.getRucniBrzda());
		javers.commit("revize",vuz.getRevize());
		javers.commit("rozmer ",vuz.getRozmer());
		javers.commit("podvozek",vuz.getPodvozek());
	}

	private List<Change> getChanges(String id, Class aClass) {
		List<Change> changes = javers.findChanges(
				QueryBuilder.byInstanceId(id, aClass).build());
		return changes;
	}

	private String selectedChange(List<Change> aList) {
		if (aList.size() > 0) {
			return javers.processChangeList(aList, new SimpleTextChangeLog());
		}
		
		return "";
	}

	private String getVuzChanges(Vuz vuz) {
		String output="";
		output+=selectedChange(getChanges(Long.toString(vuz.getId()),Vuz.class));
		output+=selectedChange(getChanges(Long.toString(vuz.getRozmer().getId()),Rozmery.class));
		output+=selectedChange(getChanges(Long.toString(vuz.getStavKm().getId()),StavKm.class));
		output+=selectedChange(getChanges(Long.toString(vuz.getPodvozek().getId()),Podvozek.class));
		output+=selectedChange(getChanges(Long.toString(vuz.getRevize().getId()),Revize.class));
		output+=selectedChange(getChanges(Long.toString(vuz.getVzduchovaBrzda().getId()),VzduchovaBrzda.class));
		output+=selectedChange(getChanges(Long.toString(vuz.getRucniBrzda().getId()),RucniBrzda.class));
		output+=selectedChange(getChanges(Long.toString(vuz.getPodvozek().getId()),Podvozek.class));

		return output;
	}
	
	private Vuz getVuzDataFromForms() {
		Vuz vuz = null;		
		
		boolean validVuzForm = vuzForm.validateForm();
		boolean validStavKmForm = stavKmForm.validateForm();
		boolean validVzduchovaBrzdaForm = vzduchovaBrzdaForm.validateForm();
		boolean validRevizeForm = revizeForm.validateForm();
		boolean validRozmeryForm = rozmeryForm.validateForm();
		boolean validRucniBrzdaForm = rucniBrzdaForm.validateForm();
		boolean validPodvozekForm = podvozekForm.validateForm();
		
		if(validVuzForm && validStavKmForm &&
				validVzduchovaBrzdaForm &&
				validRevizeForm &&
				validRozmeryForm &&
				validRucniBrzdaForm &&
				validPodvozekForm){
			vuz = vuzForm.getVuzFromBinder();
			vuz.setStavKm(stavKmForm.getStavKmFromBinder());
			vuz.setVzduchovaBrzda(vzduchovaBrzdaForm.getVzduchovaBrzdaFromBinder());
			vuz.setRevize(revizeForm.getRevizeFromBinder());
			Rozmery rozmery = rozmeryForm.getRozmeryFromBinder();
			rozmery.setLozneRozmery(rozmeryForm.getLozneRozmeryFromBinder());
			vuz.setRozmer(rozmery);
			vuz.setRucniBrzda(rucniBrzdaForm.getRucniBrzdaFromBinder());
			vuz.setPodvozek(podvozekForm.getPodvozekFromBinder());
		}
		
		return vuz;
	}

	@PostConstruct
	private void buildForm(){
		hl1.addComponent(vuzForm);
		hl1.addComponent(stavKmForm);
		hl1.addComponent(vzduchovaBrzdaForm);
		hl1.addComponent(rucniBrzdaForm);
		hl2.addComponent(revizeForm);
		hl2.addComponent(rozmeryForm);		
		hl2.addComponent(podvozekForm);

		vzduchovaBrzdaForm.setBrzdy(vzduchService);
		rucniBrzdaForm.setBrzdy(rucniService);

	}
	
	public void setButtonCaption(String caption) {
		confirmButton.setCaption(caption);
	}
	
	public void setReadOnly(){
		confirmButton.setVisible(false);
		
		vuzForm.setReadOnly();
		stavKmForm.setReadOnly();
		vzduchovaBrzdaForm.setReadOnly();
		rucniBrzdaForm.setReadOnly();
		revizeForm.setReadOnly();
		rozmeryForm.setReadOnly();
		podvozekForm.setReadOnly();
	}
	
	public void setVuz(Vuz vuz){
		vuzForm.setVuz(vuz);
		stavKmForm.setStavKm(vuz.getStavKm());
		vzduchovaBrzdaForm.setVzduchovaBrzda(vuz.getVzduchovaBrzda());
		rucniBrzdaForm.setRucniBrzda(vuz.getRucniBrzda());
		revizeForm.setRevize(vuz.getRevize());
		rozmeryForm.setRozmery(vuz.getRozmer());
		podvozekForm.setPodvozek(vuz.getPodvozek());
		commitVuz(vuz);
	}
	
	public void setCloseFunction(Runnable r) {
		this.closeFunction = r;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}
}
