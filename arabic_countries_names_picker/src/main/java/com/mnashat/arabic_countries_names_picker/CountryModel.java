package com.mnashat.arabic_countries_names_picker;

import java.io.Serializable;

public class CountryModel implements Serializable {
  private String dialCode;
  private String code;
  private String name;

  public CountryModel(String dialCode, String code, String name) {
    this.dialCode = dialCode;
    this.code = code;
    this.name = name;
  }

  public String getDialCode() {
    return dialCode;
  }

  public void setDialCode(String dialCode) {
    this.dialCode = dialCode;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
