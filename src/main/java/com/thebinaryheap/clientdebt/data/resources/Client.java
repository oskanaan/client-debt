package com.thebinaryheap.clientdebt.data.resources;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Client{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String referenceNumber;
  private BigDecimal currentBalance;
  private Date lastPaid;
  private BigDecimal lastPaidAmount;
  private BigDecimal monthlySubscription;
  private String address;
  @Column(columnDefinition = "boolean default true")
  private boolean active;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private List<Transaction> transactions;

  public Client() {
  }

  public Client(long id, String name, String referenceNumber, BigDecimal currentBalance) {
    this.id = id;
    this.name = name;
    this.referenceNumber = referenceNumber;
    this.currentBalance = currentBalance;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public BigDecimal getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(BigDecimal currentBalance) {
    this.currentBalance = currentBalance;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Date getLastPaid() {
    return lastPaid;
  }

  public void setLastPaid(Date lastPaid) {
    this.lastPaid = lastPaid;
  }

  public BigDecimal getLastPaidAmount() {
    return lastPaidAmount;
  }

  public void setLastPaidAmount(BigDecimal lastPaidAmount) {
    this.lastPaidAmount = lastPaidAmount;
  }

  public BigDecimal getMonthlySubscription() {
    return monthlySubscription;
  }

  public void setMonthlySubscription(BigDecimal monthlySubscription) {
    this.monthlySubscription = monthlySubscription;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}