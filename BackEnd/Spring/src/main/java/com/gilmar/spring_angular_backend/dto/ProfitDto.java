package com.gilmar.spring_angular_backend.dto;

import com.gilmar.spring_angular_backend.enums.TypeProfit;
import com.gilmar.spring_angular_backend.model.Stock;

public record ProfitDto (
  Long id,
  Double valueDistributed,
  TypeProfit type,
  Stock stock
){}
