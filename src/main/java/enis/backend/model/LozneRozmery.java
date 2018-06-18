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
@Table(name = "LOZNE_ROZMERY")
public class LozneRozmery implements IXLSSIMPORT,Serializable {

	private static final long serialVersionUID = -7559456068006252104L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Long loznaDelka;

	@Column
	private Long loznaPlocha;

	@Column
	private Long loznyProstor;

	@Column
	private Long vyskaLoznePlochyPrVozu;

	public LozneRozmery() {
	}

	public LozneRozmery(Long id, Long loznadelka, Long loznaplocha, Long loznyprostor, Long vyskaLoznePlochyPrVozu) {
		this.id = id;
		this.loznaDelka = loznadelka;
		this.loznaPlocha = loznaplocha;
		this.loznyProstor = loznyprostor;
		this.vyskaLoznePlochyPrVozu = vyskaLoznePlochyPrVozu;
	}

	public Long getLoznaDelka() {
		return loznaDelka;
	}

	public void setLoznaDelka(Long loznaDelka) {
		this.loznaDelka = loznaDelka;
	}

	public Long getLoznaPlocha() {
		return loznaPlocha;
	}

	public void setLoznaPlocha(Long loznaPlocha) {
		this.loznaPlocha = loznaPlocha;
	}

	public Long getLoznyProstor() {
		return loznyProstor;
	}

	public void setLoznyProstor(Long loznyProstor) {
		this.loznyProstor = loznyProstor;
	}

	public Long getVyskaLoznePlochyPrVozu() {
		return vyskaLoznePlochyPrVozu;
	}

	public void setVyskaLoznePlochyPrVozu(Long vyskaLoznePlochyPrVozu) {
		this.vyskaLoznePlochyPrVozu = vyskaLoznePlochyPrVozu;
	}

	public Long getId() {
		return id;
	}

	@Override
	public void setAttribute(String attName, String Value) {
		switch (attName.toLowerCase()) {
		case "loznadelka":
			setLoznaDelka(Long.valueOf(Value));
			break;
		case "loznaplocha":
			setLoznaPlocha(Long.valueOf(Value));
			break;
		case "loznyprostor":
			setLoznyProstor(Long.valueOf(Value));
			break;
		case "vyskalozneplochyprvozu":
			setVyskaLoznePlochyPrVozu(Long.valueOf(Value));
			break;
		}
	}
}
