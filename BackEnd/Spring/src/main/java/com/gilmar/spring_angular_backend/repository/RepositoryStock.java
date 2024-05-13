package com.gilmar.spring_angular_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilmar.spring_angular_backend.model.Stock;




@Repository //diz que essa interface será um componente do tipo repositorio para ter acesso aos metodos de um banco de dados
public interface RepositoryStock extends JpaRepository< Stock, Long> {
    //JpaRepository< Stock, Long> é a classe do jpa para gerar um repositorio recebe por padrao o tipo da classe e o tipo do id da classe
}
