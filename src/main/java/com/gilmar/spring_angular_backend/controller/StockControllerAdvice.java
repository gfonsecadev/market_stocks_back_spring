package com.gilmar.spring_angular_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.annotation.RequestScope;

import com.gilmar.spring_angular_backend.exceptions.ExceptionCustomError;

@RestControllerAdvice
public class StockControllerAdvice {

  //essa anotação serve para mostrar com qual excessão quero trabalhar
  @ExceptionHandler(ExceptionCustomError.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  // manipular a resposta para esta excessão
  public String handleNotFoundException(ExceptionCustomError ex){
    //aqui chamamos como parametro nossa excessão personalizada.
    return ex.getMessage();
  }

//metodo para lidar com excessões de validação para ficar mais amigavel para o usuário
@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public String handleNotValidException(MethodArgumentNotValidException ex){

  return ex.getBindingResult().getFieldErrors().stream()
  .map(error->error.getField() + " " + error.getDefaultMessage())
  .reduce("", (field,error)-> field + error + "\n");
  //Neste metodo percorro todos os erros pelo stream mapeio o campo que deu erro e a mensagem do erro para o reduce reduzir tudo em uma unica string a cada novo erro o reduce pula uma linha por ter colocado \n

}
/*
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleIllegalArgumentException(IllegalArgumentException ex){
    return " Deu errado!";

} */

}
