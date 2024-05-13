package com.gilmar.spring_angular_backend.enums.converters;

import java.util.stream.Stream;

import com.gilmar.spring_angular_backend.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/*Essa classe implementa essa interface que nos ajuda na transformação
   de objeto(enum) para string e string para objeto para poder
   persistir dados no banco
 *///Isso é preciso pois o banco não armazena tipos enum corretamente.

 @Converter//especifica que esta classe será um conversor.
public class CategoryConverter implements AttributeConverter<Category,String> {

  //retornará uma string para o banco.
  @Override
  public String convertToDatabaseColumn(Category category) {
    if(category == null){
      return  null;
    }
    //Retornará uma string de um enum recebido para salvar no banco (EX: RECEBEU GERAL E RETORNARÁ geral para o banco)
    return category.getValue();
  }

  //retornará um enum do banco
  @Override
  public Category convertToEntityAttribute(String value) {
    if(value==null){
      return null;
    }

    /*Retornará de string do banco(ex:geral) para enum (ex:GERAL)*/
    return  Stream.of(Category.values())
                  .filter(c->c.getValue().equals(value))
                  .findFirst()
                  .orElseThrow(IllegalArgumentException::new)  ;
  }

}
