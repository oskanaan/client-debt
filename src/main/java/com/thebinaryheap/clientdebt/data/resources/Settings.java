package com.thebinaryheap.clientdebt.data.resources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Settings {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String code;
  private String value;

  public Settings() {
  }

  public Settings(long id, String code, String value) {
    this.id = id;
    this.code = code;
    this.value = value;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}