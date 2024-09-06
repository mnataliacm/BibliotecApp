package com.ncm.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private Integer IDuser;
  private Integer member;
  private String name;
  private String surname;
  private String email;
  private String mobile;
  private String address;
  private Integer num;
  private String floor;
  private Integer CP;
  private String town;
  private String city;

  public User() {
  }

  public User(Integer iduser, String name, String surname, String email, String mobile, String address, Integer num,
      String floor, Integer cp, String town, String city, Integer member) {
    this.IDuser = iduser;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.mobile = mobile;
    this.address = address;
    this.num = num;
    this.floor = floor;
    this.CP = cp;
    this.town = town;
    this.city = city;
    this.member = member;
  }

  public Integer getIDuser() {
    return IDuser;
  }

  public void setIDuser(Integer IDuser) {
    this.IDuser = IDuser;
  }

  public Integer getMember() {
    return member;
  }

  public void setMember(Integer member) {
    this.member = member;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public Integer getCP() {
    return CP;
  }

  public void setCP(Integer CP) {
    this.CP = CP;
  }

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

}