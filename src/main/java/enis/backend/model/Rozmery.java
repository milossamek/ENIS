package enis.backend.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enis.backend.importdata.IXLSSIMPORT;

@Entity
public class Rozmery implements IXLSSIMPORT,Serializable {
	
	private static final long serialVersionUID = -6281182895600736697L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private Long delkaPresNarazniky;

	@ManyToOne(optional = false, fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private LozneRozmery lozneRozmery;

	public Rozmery() {
	}

  public Rozmery(LozneRozmery loznerozmery) {
    this.lozneRozmery = loznerozmery;
  }

	public Rozmery(Long id, Long delkapresnarazniky, LozneRozmery loznerozmery) {
		this.id = id;
		this.delkaPresNarazniky = delkapresnarazniky;
		this.lozneRozmery = loznerozmery;
	}

	public Long getDelkaPresNarazniky() {
		return delkaPresNarazniky;
	}

	public void setDelkaPresNarazniky(Long delkaPresNarazniky) {
		this.delkaPresNarazniky = delkaPresNarazniky;
	}

	public LozneRozmery getLozneRozmery() {
		return lozneRozmery;
	}

	public void setLozneRozmery(LozneRozmery lozneRozmery) {
		this.lozneRozmery = lozneRozmery;
	}

	public Long getId() {
		return id;
	}

@Override
  public void setAttribute(String attName, String Value) {
    switch (attName.toLowerCase()) {
      case "delkapresnarazniky": setDelkaPresNarazniky(Long.valueOf(Value));
        break;
    }
  }
}
