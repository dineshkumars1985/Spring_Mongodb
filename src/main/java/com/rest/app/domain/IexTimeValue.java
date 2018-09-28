package com.rest.app.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dinesh Kumar
 */

public class IexTimeValue {

  private String ts;
  private Double price;

  public IexTimeValue(String ts, Double price){
    this.ts = ts;
    this.price = price;
  }

  public String getTs() {
    return ts;
  }

  public void setTs(String ts) {
    this.ts = ts;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
