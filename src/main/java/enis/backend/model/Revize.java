package enis.backend.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enis.backend.importdata.IXLSSIMPORT;

@Entity
public class Revize implements IXLSSIMPORT,Serializable {
	
	private static final long serialVersionUID = -4845311820714977761L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private LocalDate datumPosRevize;
	
	@Column
	private Long revizLhuta;

	public Revize() {
	}

	public Revize(Long id, LocalDate datumposrevize, Long revizlhuta) {
		this.id = id;
		this.datumPosRevize = datumposrevize;
		this.revizLhuta = revizlhuta;
	}

public LocalDate getDatumPosRevize() {
		return datumPosRevize;
	}

	public void setDatumPosRevize(LocalDate datumPosRevize) {
		this.datumPosRevize = datumPosRevize;
	}

	public Long getRevizLhuta() {
		return revizLhuta;
	}

	public void setRevizLhuta(Long revizLhuta) {
		this.revizLhuta = revizLhuta;
	}

	public Long getId() {
		return id;
	}

@Override
  public void setAttribute(String attName, String Value) {
    switch (attName.toLowerCase()) {
      case "datumposrevize": setDatumPosRevize(null);
      break;
      case "revizlhuta": setRevizLhuta(Long.valueOf(Value));
        break;
    }
  }
}
