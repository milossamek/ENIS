package enis.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enis.backend.importdata.IXLSSIMPORT;

@Entity
public class VzduchovaBrzda implements IXLSSIMPORT,Serializable {
	
	private static final long serialVersionUID = -5994394303757661034L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column
	private Long druhVzduchoveBrzdy;

	@Column
	private Long brzdiciHmotnostVzduchBrzdy;

	public VzduchovaBrzda() {
	}

	public VzduchovaBrzda(Long id, Long druhvzduchovebrzdy, Long brzdicihmotnostvzduchbrzdy) {
		this.id = id;
		this.druhVzduchoveBrzdy = druhvzduchovebrzdy;
		this.brzdiciHmotnostVzduchBrzdy = brzdicihmotnostvzduchbrzdy;
	}

	public Long getDruhVzduchoveBrzdy() {
		return druhVzduchoveBrzdy;
	}

	public void setDruhVzduchoveBrzdy(Long druhVzduchoveBrzdy) {
		this.druhVzduchoveBrzdy = druhVzduchoveBrzdy;
	}

	public Long getBrzdiciHmotnostVzduchBrzdy() {
		return brzdiciHmotnostVzduchBrzdy;
	}

	public void setBrzdiciHmotnostVzduchBrzdy(Long brzdiciHmotnostVzduchBrzdy) {
		this.brzdiciHmotnostVzduchBrzdy = brzdiciHmotnostVzduchBrzdy;
	}

	public Long getId() {
		return id;
	}

@Override
  public void setAttribute(String attName, String Value) {
	  switch (attName.toLowerCase()) {
		  case "druhvzduchovebrzdy": setDruhVzduchoveBrzdy(Long.valueOf(Value));
			  break;
		  case "brzdicihmotnostvzduchbrzdy": setBrzdiciHmotnostVzduchBrzdy(Long.valueOf(Value));
			  break;
	  }
  }
}
