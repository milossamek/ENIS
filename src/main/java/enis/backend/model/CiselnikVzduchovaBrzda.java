package enis.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ciselnik_vzduchova_brzda")
public class CiselnikVzduchovaBrzda {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String druh;

  public CiselnikVzduchovaBrzda() {
  }

  public CiselnikVzduchovaBrzda(Long id, String druh) {
    this.id = id;
    this.druh = druh;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDruh() {
    return druh;
  }

  public void setDruh(String druh) {
    this.druh = druh;
  }

  @Override
  public String toString() {
    return druh;
  }
}
