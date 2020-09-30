package com.bezkoder.spring.data.cassandra.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class FacebookTokens {

  @PrimaryKey
  private String token;

  private boolean valid;
  private boolean statusUse;
  private Date lastTimeUsed;
  private int countUse;
  private Date createdTime;


  public FacebookTokens() {

  }

  public FacebookTokens(String token, boolean valid, boolean statusUse, Date lastTimeUsed, int countUse, Date createdTime) {
    this.token = token;
    this.valid = valid;
    this.statusUse = statusUse;
    this.lastTimeUsed = lastTimeUsed;
    this.countUse = countUse;
    this.createdTime = createdTime;
  }

  public String getToken() {
    return token;
  }

  public Date getLastTimeUsed() {
    return lastTimeUsed;
  }

  public void setLastTimeUsed(Date lastTimeUsed) {
    this.lastTimeUsed = lastTimeUsed;
  }

  public int getCountUse() {
    return countUse;
  }

  public void setCountUse(int countUse) {
    this.countUse = countUse;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public boolean isStatusUse() {
    return statusUse;
  }

  public void setStatusUse(boolean statusUse) {
    this.statusUse = statusUse;
  }

}
