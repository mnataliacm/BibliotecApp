package com.ncm.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Editorial {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer IDeditorial;
  private String name;

  public Integer getIDeditorial() {
    return IDeditorial;
  }

  public void setIDeditorial(Integer IDeditorial) {
    this.IDeditorial = IDeditorial;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}