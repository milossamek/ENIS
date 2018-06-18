package enis.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enis.backend.importdata.IXLSSIMPORT;

@Entity
@Table(name = "STAV_KM")
public class StavKm implements IXLSSIMPORT,Serializable {
	
	private static final long serialVersionUID = 5487711841060421589L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Long najetKm;

	@Column
	private Long zbyvajiciKm;

	public StavKm() {
	}

	public StavKm(Long id, Long najetkm, Long zbyvajicikm) {
		this.id = id;
		this.najetKm = najetkm;
		this.zbyvajiciKm = zbyvajicikm;
	}

	public Long getNajetKm() {
		return najetKm;
	}

	public void setNajetKm(Long najetKm) {
		this.najetKm = najetKm;
	}

	public Long getZbyvajiciKm() {
		return zbyvajiciKm;
	}

	public void setZbyvajiciKm(Long zbyvajiciKm) {
		this.zbyvajiciKm = zbyvajiciKm;
	}

	public Long getId() {
		return id;
	}

	@Override
	public void setAttribute(String attName, String Value) {
		switch (attName.toLowerCase()) {
		case "najetkm":
			setNajetKm(Long.valueOf(Value));
			break;
		case "zbyvajicikm":
			setZbyvajiciKm(Long.valueOf(Value));
			break;
		}
	}

	@Override
	public String toString() {
		return "StavKm{" +
				"id=" + id +
				", najetKm=" + najetKm +
				", zbyvajiciKm=" + zbyvajiciKm +
				'}';
	}
}
