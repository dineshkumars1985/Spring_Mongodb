package com.rest.app.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Dinesh Kumar
 */

public class IexFetchModel {

  private List<String> symbol;
  private Timestamp from_dt;
  private Timestamp to_dt;

  public List<String> getSymbol() {
    return symbol;
  }

  public void setSymbol(List<String> symbol) {
    this.symbol = symbol;
  }

  public Timestamp getFrom_dt() {
    return from_dt;
  }

  public void setFrom_dt(Timestamp from_dt) {
    this.from_dt = from_dt;
  }

  public Timestamp getTo_dt() {
    return to_dt;
  }

  public void setTo_dt(Timestamp to_dt) {
    this.to_dt = to_dt;
  }
}
