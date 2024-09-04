package com.ncm.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  
  private Integer idauthor;
  private String name;

  public Integer getidauthor() {
    return idauthor;
  }

  public void setidauthor(Integer idauthor) {
    this.idauthor = idauthor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
