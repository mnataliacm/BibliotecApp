package com.ncm.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private Long ISBN;
  private String title;
  private Integer year;
  private Integer IDgenre;
  private Integer IDauthor;
  private Integer IDeditorial;
  
  public Long getISBN() {
    return ISBN;
  }
  public void setISBN(Long iSBN) {
    ISBN = iSBN;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Integer getYear() {
    return year;
  }
  public void setYear(Integer year) {
    this.year = year;
  }
  public Integer getIDgenre() {
    return IDgenre;
  }
  public void setIDgenre(Integer iDgenre) {
    IDgenre = iDgenre;
  }
  public Integer getIDauthor() {
    return IDauthor;
  }
  public void setIDauthor(Integer iDauthor) {
    IDauthor = iDauthor;
  }
  public Integer getIDeditorial() {
    return IDeditorial;
  }
  public void setIDeditorial(Integer iDeditorial) {
    IDeditorial = iDeditorial;
  }

  

}
