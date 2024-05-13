package com.gilmar.spring_angular_backend.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gilmar.spring_angular_backend.dto.StockDto;
import com.gilmar.spring_angular_backend.dto.StockPageDto;
import com.gilmar.spring_angular_backend.dto.dto_mapper.StockDtoMapper;
import com.gilmar.spring_angular_backend.exceptions.ExceptionCustomError;
import com.gilmar.spring_angular_backend.model.Stock;
import com.gilmar.spring_angular_backend.repository.RepositoryStock;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/* ****A classe serviço é a responsavel de receber um objeto dto e se preciso
  utilizar o mapper criado para fazer a conversão para model para que seja capaz
  de salvar no banco depois se for preciso converte a resposta do banco de model para dto novamente
  isso tudo utilizando o padrão de Mapppers
 */

@Service
public class StockService {

  RepositoryStock repository;
  StockDtoMapper mapper;

  //construtor
  public StockService(RepositoryStock repository,StockDtoMapper mapper) {
    this.repository = repository;
    this.mapper=mapper;
  }


  //METODO PARA LISTAR TUDO com paginação.
  public StockPageDto list(int pag, int size){
    //A clausa where foi modificada no model utilizando Hibernate para retornar todos id onde show=true pois implementei um softDelete com essa coluna
    //Paginando a exibição da lista com o objeto Page.
    Page<Stock> stockPage= repository.findAll(PageRequest.of(pag, size));
    List<StockDto> stockDto=stockPage.stream().map(mapper::toDTO).collect(Collectors.toList());

    return new StockPageDto(stockDto,stockPage.getTotalElements());

   }



/*
  //METODO PARA LISTAR TUDO sem paginação
  public List<StockDto> list(){
  //A clausa where foi modificada no model utilizando Hibernate para retornar todos id onde show=true
  return  repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
 }//utilizo stream para retornar um por um para ser mapeado e convertido pelo mapper criado e no final e convertido para lista

 */

  //METODO PARA ATUALIZAR
  public StockDto updateStock( @NotNull @Positive Long id, @RequestBody @Valid StockDto stock){
    //Antes de atualizar precisamos procurar o registro original no banco.
    return repository.findById(id).map(found->{
            //Se houver resposta nos atualizamos os dados e salvamos novamente.
                                        found.setName(stock.name());
                                        found.setCategory(mapper.stringToEnum(stock.category()));
                                        found.setPrice_medium(stock.price_medium());
                                        found.getProfits().clear();
                                        var list=mapper.toStock(stock).getProfits();

                                        list.stream().forEach(profit->{

                                         found.getProfits().add(profit);
                                        });
                                        found.getProfits();


                                        //found.setProfits(mapper.toProfit(stock.profits()));
                                        //found.setProfits(mapper.toStock(stock.profits()));
                                        return mapper.toDTO(repository.save(found)); } )
                                                     .orElseThrow(()->new ExceptionCustomError(id));
  }



  //METODO PARA PROCURAR
  public StockDto findStock( @NotNull @Positive Long id){//essa anotação recupera o parametro da rota
    return repository.findById(id).map(mapper::toDTO).orElseThrow(()->new ExceptionCustomError(id));
  }



  //METODO PARA DELETAR
  public void delete( @NotNull @Positive Long id){
     //o metodo .delete foi modificado no model para colocar a coluna show deste id como false.
     repository.delete(repository.findById(id)
                        .orElseThrow(()->new ExceptionCustomError(id)));

    }



  //MEDODO PARA ADICIONAR
  public StockDto add(@Valid StockDto stock){
    //recebo um dto como parametro converto com o mapper para model para ser salvo no banco e depois converto para dto novamente.
    return mapper.toDTO(repository.save(mapper.toStock(stock)));
   }


}


