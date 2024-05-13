package com.gilmar.spring_angular_backend.exceptions;

public class ExceptionCustomError extends RuntimeException {
  //Construtor
  public ExceptionCustomError(Long id){
    //O super da classe pai é chamado esperando a mensagem de erro
    super("empresa não encontrada com o id: "+id);

  }

}
