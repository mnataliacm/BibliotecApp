package com.ncm.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  
  private Integer IDauthor;
  private String name;

  public Integer getIDauthor() {
    return IDauthor;
  }

  public void setIDauthor(Integer iDauthor) {
    this.IDauthor = iDauthor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
