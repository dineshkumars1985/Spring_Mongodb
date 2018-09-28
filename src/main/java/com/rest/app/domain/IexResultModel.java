package com.rest.app.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dinesh Kumar
 */

public class IexResultModel {

  private String name;
  private String logo;
  private List<IexTimeValue> timevalue;

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

  public List<IexTimeValue> getTimevalue() {
    if(timevalue == null){
      timevalue = new ArrayList<IexTimeValue>();
    }
    return timevalue;
  }

  public void setTimevalue(List<IexTimeValue> timevalue) {
    if(timevalue == null){
      timevalue = new ArrayList<IexTimeValue>();
    }
    this.timevalue = timevalue;
  }

  public void addSingleObject(IexTimeValue val) {
    if(timevalue == null){
      timevalue = new ArrayList<IexTimeValue>();
    }
    this.timevalue.add(val);
  }
}
