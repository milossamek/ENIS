package enis.backend.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import enis.backend.importdata.IXLSSIMPORT;

@Entity
public class Vuz implements IXLSSIMPORT, Serializable {

	private static final long serialVersionUID = -8766265969932524602L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column
	private Long vuzCis;
	
	@NotNull
	@Enumerated
	@Column
	private StavVozu stavVozu;
	
	@Column
	private Long vlastniHmotnost;

	@Column
	private Long maxRychlost;

	@ManyToOne(optional = false, fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private Podvozek podvozek;

	@ManyToOne(optional = false, fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private Revize revize;

	@ManyToOne(optional = false, fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private Rozmery rozmer;

	@ManyToOne(optional = false, fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private RucniBrzda rucniBrzda;

	@ManyToOne(optional = false, fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private VzduchovaBrzda vzduchovaBrzda;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private StavKm stavKm;

	public Vuz() {
	}

	public Vuz(Long vuzCis, StavVozu stavVozu, Long vlastniHmotnost, Long maxRychlost, Podvozek podvozek, Revize revize, Rozmery rozmer, RucniBrzda rucniBrzda, VzduchovaBrzda vzduchovaBrzda, StavKm stavKm) {
		this.vuzCis = vuzCis;
		this.stavVozu = stavVozu;
		this.vlastniHmotnost = vlastniHmotnost;
		this.maxRychlost = maxRychlost;
		this.podvozek = podvozek;
		this.revize = revize;
		this.rozmer = rozmer;
		this.rucniBrzda = rucniBrzda;
		this.vzduchovaBrzda = vzduchovaBrzda;
		this.stavKm = stavKm;
	}

	public Vuz(Podvozek podvozek, Revize revize, Rozmery rozmer, RucniBrzda rucniBrzda, VzduchovaBrzda vzduchovabrzda,StavKm stavKm) {
		stavVozu=StavVozu.PROVOZ;
		vlastniHmotnost=(long) 0;
		maxRychlost=(long) 0;
	     this.podvozek = podvozek;
	    this.revize = revize;
	    this.rozmer = rozmer;
	    this.rucniBrzda = rucniBrzda;
	    this.vzduchovaBrzda = vzduchovabrzda;
	    this.stavKm=stavKm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Podvozek getPodvozek() {
		return podvozek;
	}

	public void setPodvozek(Podvozek podvozek) {
		this.podvozek = podvozek;
	}

	public Revize getRevize() {
		return revize;
	}

	public void setRevize(Revize revize) {
		this.revize = revize;
	}

	public Rozmery getRozmer() {
		return rozmer;
	}

	public void setRozmer(Rozmery rozmer) {
		this.rozmer = rozmer;
	}

	public RucniBrzda getRucniBrzda() {
		return rucniBrzda;
	}

	public void setRucniBrzda(RucniBrzda rucniBrzda) {
		this.rucniBrzda = rucniBrzda;
	}

	public Long getVuzCis() {
		return vuzCis;
	}

	public void setVuzCis(Long vuzCis) {
		this.vuzCis = vuzCis;
	}

	public Long getVlastniHmotnost() {
		return vlastniHmotnost;
	}

	public void setVlastniHmotnost(Long vlastniHmotnost) {
		this.vlastniHmotnost = vlastniHmotnost;
	}

	public Long getMaxRychlost() {
		return maxRychlost;
	}

	public void setMaxRychlost(Long maxRychlost) {
		this.maxRychlost = maxRychlost;
	}

	public VzduchovaBrzda getVzduchovaBrzda() {
		return vzduchovaBrzda;
	}

	public void setVzduchovaBrzda(VzduchovaBrzda vzduchovaBrzda) {
		this.vzduchovaBrzda = vzduchovaBrzda;
	}

	public StavVozu getStavVozu() {
		return stavVozu;
	}

	public void setStavVozu(StavVozu stavVozu) {
		this.stavVozu = stavVozu;
	}

	public StavKm getStavKm() {
		return stavKm;
	}

	public void setStavKm(StavKm stavKm) {
		this.stavKm = stavKm;
	}

	@Override
  public void setAttribute(String attName, String Value) {
    switch (attName.toLowerCase()) {
      case "vuzcis": setVuzCis(Long.valueOf(Value));
      break;
      case "vlastnihmotnost": setVlastniHmotnost(Long.valueOf(Value));
      break;
      case "maxrychlost": setMaxRychlost(Long.valueOf(Value));
        break;
    }
  }
}
