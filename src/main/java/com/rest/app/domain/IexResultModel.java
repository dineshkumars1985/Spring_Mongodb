package com.rest.app.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dinesh Kumar
 */

public class IexResultModel {

  private String name;
  private String logo;
  private List<Double> price;

  public IexResultModel(String name, String logo){
    this.name = name;
    this.logo = logo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public List<Double> getPrice() {
    if(price == null){
      price = new ArrayList<Double>();
    }
    return price;
  }

  public void setPrice(List<Double> price) {
    if(price == null){
      price = new ArrayList<Double>();
    }
    this.price = price;
  }

  public void addSinglePrice(Double priceval) {
    if(price == null){
      price = new ArrayList<Double>();
    }
    this.price.add(priceval);
  }
}
