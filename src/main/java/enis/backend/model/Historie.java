package enis.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Historie implements Serializable {

	private static final long serialVersionUID = 6222945902049043936L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Uzivatel uzivatel;
	
	@Column
	private String operace;
	
	@Column
	private Date datumZmeny;

	@Column
	private String detail;

	public Historie() {
	}

	public Historie(Long id, Uzivatel uzivatel, String operace, Timestamp datumzmeny) {
		this.id = id;
		this.uzivatel = uzivatel;
		this.operace = operace;
		this.datumZmeny = datumzmeny;
	}

	public Historie(Uzivatel uzivatel, String operace, Date datumZmeny, String detail) {
		this.uzivatel = uzivatel;
		this.operace = operace;
		this.datumZmeny = datumZmeny;
		this.detail = detail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Uzivatel getUzivatel() {
		return uzivatel;
	}

	public void setUzivatel(Uzivatel uzivatel) {
		this.uzivatel = uzivatel;
	}

	public String getOperace() {
		return operace;
	}

	public void setOperace(String operace) {
		this.operace = operace;
	}

	public Date getDatumZmeny() {
		return datumZmeny;
	}

	public void setDatumZmeny(Date datumZmeny) {
		this.datumZmeny = datumZmeny;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
