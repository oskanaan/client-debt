package com.thebinaryheap.clientdebt.data.resources;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String dueMonth;
  private String dueYear;
  private BigDecimal amountDue;
  private BigDecimal totalAmountPaid;

  @Column(name = "client_id")
  private long clientId;

  public Payment() {
  }

  public Payment(String dueMonth, String dueYear, BigDecimal amountDue, BigDecimal totalAmountPaid, long clientId) {
    this.dueMonth = dueMonth;
    this.dueYear = dueYear;
    this.amountDue = amountDue;
    this.totalAmountPaid = totalAmountPaid;
    this.clientId = clientId;
  }

  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
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

  public BigDecimal getAmountDue() {
    return amountDue;
  }

  public void setAmountDue(BigDecimal amountDue) {
    this.amountDue = amountDue;
  }

  public BigDecimal getTotalAmountPaid() {
    return totalAmountPaid;
  }

  public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
    this.totalAmountPaid = totalAmountPaid;
  }

  public long getClientId() {
    return clientId;
  }
  
  public void setClientId(long clientId) {
    this.clientId = clientId;
  }
}
