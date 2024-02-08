package com.gilmar.spring_angular_backend.enums;

public enum Category {
  /*para definir valores para os enums é preciso
  os valor estar iniciados no construtor*/
  //Os enums devem ser seguidos de virgula e terminar a lista com o sinal ;
  GERAL("geral"),
  TECHNOLOGY("tech"),
  CONSTRUCTION("constr"),
  AGRIBUSINESS("agrib"),
  FOOD_AND_DRINKS("foodDrink"),
  EDUCATION("educ"),
  FINANCE("finance"),
  ELETRIC("eletr"),
  MALL("mall"),
  MINING_AND_STEEL("miniSteel");


  private String value;

  /*os construtores devem ser privados para
  não serem instanciados*/
  private Category(String value) {
    this.value = value;
  }

  //get para recuperar o value
  public String getValue(){
    return value;
  }

  //converte a variavel no tipo texto.
  @Override
  public String toString() {
    return value;
  }



}
