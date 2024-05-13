package com.gilmar.spring_angular_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.gilmar.spring_angular_backend.enums.TypeProfit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Profit {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
   private Long id;


   @Column(nullable=false)
   private Double valueDistributed;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private TypeProfit type;

   //muitos lucros pra uma ação
   @ManyToOne(fetch = FetchType.LAZY)
   @JsonProperty(access = Access.WRITE_ONLY)//ESSA ANOTAÇÃO SERVE PARA DIZER QUE esse atributo somente será escrito para derialização(set) impedindo que aconteca referencia circular
   private Stock stock;

   public Long getId() {
    return id;
   }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getValueDistributed() {
    return valueDistributed;
  }

  public void setValueDistributed(Double valueDistributed) {
    this.valueDistributed = valueDistributed;
  }

  public TypeProfit getType() {
    return type;
  }

  public void setType(TypeProfit type) {
    this.type = type;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }
}
