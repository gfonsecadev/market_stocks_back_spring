package com.gilmar.spring_angular_backend.enums.validations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gilmar.spring_angular_backend.dto.dto_mapper.StockDtoMapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//Essa classe será usada pela interface de validação criada
/*Ela implementa a interface abaixo que recebe entre <>
 o nome da interface e o tipo recebido na validação*/
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {
  private List<String> acceptedValues;

  //aqui a variavel dada será preenchida com um lista dos dados do enum passado.
  @Override
  public void initialize(ValueOfEnum annotation) {
      acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
              .map(x->x.toString())
              .collect(Collectors.toList());
  }

  //esse é o metodo que irá validar tudo.
  @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        //aqui será verificado se há o valor passado existe na lista preenchida no metodo initialize com os dados da classe enum passadal
        return acceptedValues.contains(value);
    }
}
