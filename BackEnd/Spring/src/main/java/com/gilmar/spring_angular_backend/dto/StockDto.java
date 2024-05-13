package com.gilmar.spring_angular_backend.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.gilmar.spring_angular_backend.enums.Category;
import com.gilmar.spring_angular_backend.enums.validations.ValueOfEnum;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;


//todos os dados do banco até o cliente ou do cliente até o banco serão trafegados atrávez de um Dto e não do model o model representa somente a tabela da api.
//Records são classes imutáveis que requerem apenas o tipo e o nome do atributo depois de declarados são imutáveis.
public record StockDto(
    Long id,
   @NotBlank @Length(min = 3,max = 100) String name,
   @NotNull @DecimalMin(value="0.1") Double price_medium,
   @NotNull @ValueOfEnum(enumClass = Category.class, message="Area de atuação não válida com nenhuma categoria.") String category,
    List<ProfitDto> profits
) {
}
