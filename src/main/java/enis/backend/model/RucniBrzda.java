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
@Table(name = "RUCNI_BRZDA")
public class RucniBrzda implements IXLSSIMPORT,Serializable {
	
	private static final long serialVersionUID = -2010848559286095271L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column
	private Long druhRucniBrzdy;
	
	@Column
	private Long brzdiciHmotnostRucniBrzdy;

	public RucniBrzda() {
	}

	public RucniBrzda(Long id, Long druhrucnibrzdy, Long brzdicihmotnostrucnibrzdy) {
		this.id = id;
		this.druhRucniBrzdy = druhrucnibrzdy;
		this.brzdiciHmotnostRucniBrzdy = brzdicihmotnostrucnibrzdy;
	}

  public Long getDruhRucniBrzdy() {
		return druhRucniBrzdy;
	}

	public void setDruhRucniBrzdy(Long druhRucniBrzdy) {
		this.druhRucniBrzdy = druhRucniBrzdy;
	}

	public Long getBrzdiciHmotnostRucniBrzdy() {
		return brzdiciHmotnostRucniBrzdy;
	}

	public void setBrzdiciHmotnostRucniBrzdy(Long brzdiciHmotnostRucniBrzdy) {
		this.brzdiciHmotnostRucniBrzdy = brzdiciHmotnostRucniBrzdy;
	}

	public Long getId() {
		return id;
	}


@Override
  public void setAttribute(String attName, String Value) {
    switch (attName.toLowerCase()) {
      case "druhrucnibrzdy": setDruhRucniBrzdy(Long.valueOf(Value));
      break;
      case "brzdicihmotnostrucnibrzdy": setBrzdiciHmotnostRucniBrzdy(Long.valueOf(Value));
      break;
    }
  }
}
