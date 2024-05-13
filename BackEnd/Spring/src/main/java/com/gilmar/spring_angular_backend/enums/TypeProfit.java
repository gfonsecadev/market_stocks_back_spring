package com.gilmar.spring_angular_backend.enums;

public enum TypeProfit {
  JCP("juro sobre capital proprio"),
  DY("dividendo");

  private String value;

  private TypeProfit(String value) {
    this.value = value;
  }

  public String getValor(){
    return this.value;
  }

}
