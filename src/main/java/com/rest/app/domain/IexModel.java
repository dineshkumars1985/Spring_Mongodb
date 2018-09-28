package com.rest.app.domain;

/**
 * @author Dinesh Kumar
 */
public class IexModel {

  private IexPk _id;
  private String name;
  private Double price;
  private String logo;

  public IexModel(IexPk id, String name, Double price, String logo){
    this._id = id;
    this.name = name;
    this.price = price;
    this.logo = logo;
  }

  public IexPk get_id() {
    return _id;
  }

  public void set_id(IexPk _id) {
    this._id = _id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }
}
