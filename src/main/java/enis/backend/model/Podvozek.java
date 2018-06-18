package enis.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enis.backend.importdata.IXLSSIMPORT;

@Entity
public class Podvozek implements IXLSSIMPORT,Serializable {

	private static final long serialVersionUID = -6973191250076718986L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Long vzdalOtocCepuPodvozku;
	
	@Column
	private Long rozvorPodvozku;
	
	@Column
	private Long unosnostKonstrukcni;
	
	@Column
	private Long pocetPodvozu;
	
	@Column
	private Long pocetClanku;
	
	@Column
	private Long pocetNaprav;

	public Podvozek() {
	}

	public Podvozek(Long id, Long vzdalotoccepupodvozku, Long rozvorpodvozku, Long unosnostkonstrukcni,
			Long pocetpodvozu, Long pocetclanku, Long pocetnaprav) {
		this.id = id;
		this.vzdalOtocCepuPodvozku = vzdalotoccepupodvozku;
		this.rozvorPodvozku = rozvorpodvozku;
		this.unosnostKonstrukcni = unosnostkonstrukcni;
		this.pocetPodvozu = pocetpodvozu;
		this.pocetClanku = pocetclanku;
		this.pocetNaprav = pocetnaprav;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVzdalOtocCepuPodvozku() {
		return vzdalOtocCepuPodvozku;
	}

	public void setVzdalOtocCepuPodvozku(Long vzdalOtocCepuPodvozku) {
		this.vzdalOtocCepuPodvozku = vzdalOtocCepuPodvozku;
	}

	public Long getRozvorPodvozku() {
		return rozvorPodvozku;
	}

	public void setRozvorPodvozku(Long rozvorPodvozku) {
		this.rozvorPodvozku = rozvorPodvozku;
	}

	public Long getUnosnostKonstrukcni() {
		return unosnostKonstrukcni;
	}

	public void setUnosnostKonstrukcni(Long unosnostKonstrukcni) {
		this.unosnostKonstrukcni = unosnostKonstrukcni;
	}

	public Long getPocetPodvozu() {
		return pocetPodvozu;
	}

	public void setPocetPodvozu(Long pocetPodvozu) {
		this.pocetPodvozu = pocetPodvozu;
	}

	public Long getPocetClanku() {
		return pocetClanku;
	}

	public void setPocetClanku(Long pocetClanku) {
		this.pocetClanku = pocetClanku;
	}

	public Long getPocetNaprav() {
		return pocetNaprav;
	}

	public void setPocetNaprav(Long pocetNaprav) {
		this.pocetNaprav = pocetNaprav;
	}

@Override
  public void setAttribute(String attName, String Value) {
    switch (attName.toLowerCase()) {
      case "vzdalotoccepupodvozku": setVzdalOtocCepuPodvozku(Long.valueOf(Value));
      break;
      case "rozvorpodvozku": setRozvorPodvozku(Long.valueOf(Value));
      break;
      case "unosnostkonstrukcni": setUnosnostKonstrukcni(Long.valueOf(Value));
      break;
      case "pocetpodvozu": setPocetPodvozu(Long.valueOf(Value));
      break;
      case "pocetclanku": setPocetClanku(Long.valueOf(Value));
      break;
      case "pocetnaprav": setPocetNaprav(Long.valueOf(Value));
        break;
    }
  }

	@Override
	public String toString() {
		return "Podvozek{" +
				"id=" + id +
				", vzdalOtocCepuPodvozku=" + vzdalOtocCepuPodvozku +
				", rozvorPodvozku=" + rozvorPodvozku +
				", unosnostKonstrukcni=" + unosnostKonstrukcni +
				", pocetPodvozu=" + pocetPodvozu +
				", pocetClanku=" + pocetClanku +
				", pocetNaprav=" + pocetNaprav +
				'}';
	}
}
