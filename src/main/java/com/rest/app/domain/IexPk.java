package com.rest.app.domain;

import java.sql.Timestamp;

/**
 * @author Dinesh Kumar
 */

public class IexPk {

  private String symbol;
  private Timestamp insert_ts;

  public IexPk(String symbol, Timestamp insert_ts){
    this.symbol = symbol;
    this.insert_ts = insert_ts;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Timestamp getInsert_ts() {
    return insert_ts;
  }

  public void setInsert_ts(Timestamp insert_ts) {
    this.insert_ts = insert_ts;
  }
}
