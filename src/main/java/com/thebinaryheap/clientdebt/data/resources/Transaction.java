package com.thebinaryheap.clientdebt.data.resources;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date onDate;
  private String dueMonth;
  private String dueYear;
  private BigDecimal amountPaid;
  @Column(name = "client_id")
  private long clientId;

  @Column(columnDefinition = "boolean default false")
  private boolean imported;

  public Transaction(){

  }

  public Transaction(long id, Date onDate, String dueMonth, String dueYear, BigDecimal amountPaid) {
    this.id = id;
    this.onDate = onDate;
    this.dueMonth = dueMonth;
    this.dueYear = dueYear;
    this.amountPaid = amountPaid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getOnDate() {
    return onDate;
  }

  public void setOnDate(Date onDate) {
    this.onDate = onDate;
  }

  public BigDecimal getAmountPaid() {
    return amountPaid;
  }

  public void setAmountPaid(BigDecimal amountPaid) {
    this.amountPaid = amountPaid;
  }

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

  public boolean isImported() {
    return imported;
  }

  public void setImported(boolean imported) {
    this.imported = imported;
  }

  public String getDueMonth() {
    return dueMonth;
  }

  public void setDueMonth(String dueMonth) {
    this.dueMonth = dueMonth;
  }

  public String getDueYear() {
    return dueYear;
  }

  public void setDueYear(String dueYear) {
    this.dueYear = dueYear;
  }
}