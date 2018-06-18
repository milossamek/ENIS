package enis.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import enis.backend.importdata.IXLSSIMPORT;

@Entity
public class Role implements IXLSSIMPORT, Serializable, GrantedAuthority {
	
	private static final long serialVersionUID = -1614041332265956267L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nazev;

	public Role() {
	}

	public Role(Long id, String nazev) {
		this.id = id;
		this.nazev = nazev;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

  @Override
  public void setAttribute(String attName, String Value) {

  }

	@Override
	public String toString() {
		return nazev;
	}

	@Override
	public String getAuthority() {
		return nazev;
	}
}
