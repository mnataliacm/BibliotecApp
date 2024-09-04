package com.ncm.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer IDgenre;
  private String name;

  public Integer getIDgenre() {
    return IDgenre;
  }

  public void setIDgenre(Integer IDgenre) {
    this.IDgenre = IDgenre;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}