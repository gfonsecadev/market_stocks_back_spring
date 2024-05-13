package com.gilmar.spring_angular_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gilmar.spring_angular_backend.dto.StockDto;
import com.gilmar.spring_angular_backend.dto.StockPageDto;
import com.gilmar.spring_angular_backend.service.StockService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated//Garante que as validações serão feitas.
@RestController // sera um bean do tipo rest somente para requisição sem retorno de uma view.
@RequestMapping("/api/stocks")//quando acessar essa rota esse controller retornará resposta
/* @AllArgsConstructor() para criar o construtor e injetar instacias pelo Lombok */
public class StockController {

/*  @Autowired  pode fazer a injeção através desta anotação ou pelo construtor
RepositoryStock repository; */

//variavel que contem implementação do jpa
private final StockService service;
//usando construtor para injetar instancias.
public StockController(StockService service) {

  this.service=service;

}



//Iremos retornar todos os dados.

@GetMapping
 public StockPageDto list(@RequestParam(defaultValue = "0") int pag, @RequestParam(defaultValue = "10") @Max(20) int size ){
  //utilizei soft delete.
  //A clausa where foi modificada no model utilizando Hibernate para retornar todos id onde show=true
  return this.service.list(pag,size);
 }



 //iremos receber uma requisição deste tipo para atualizar dados.
 @PutMapping("/{id}")
 public StockDto updateStock(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid StockDto StockDto){
    //Antes de atualizar precisamos procurar o registro original no banco.
    return service.updateStock(id,StockDto);

 }



 //iremos buscar o dados no banco.
 @GetMapping("/{id}")
 //Aqui receberemos um id na rota deste controller
  public StockDto findStock(@PathVariable @NotNull @Positive Long id){//essa anotação recupera o parametro da rota
  //Recebendo a resposta como um ResponseEntity conseguimos manipular o corpo da requisição se houve resposta ou erro.
  return service.findStock(id);
 }



 //iremos fazer o softDelete.
 @DeleteMapping("/{id}")
 @ResponseStatus(HttpStatus.NO_CONTENT)//retorno
 public void delete(@PathVariable @NotNull @Positive Long id){
    service.delete(id);

 }



 //iremos inserir dados no banco
 @ResponseStatus(HttpStatus.CREATED)//serve como o ResponseEntity mas somente para manipular o status
 @PostMapping//função que será chamada para metodo post
 public StockDto add(@RequestBody @Valid StockDto StockDto){
    return service.add(StockDto);
 }


 //ou-----


/*@PostMapping
  public ResponseEntity<StockDto> add( @RequestBody @Valid StockDto StockDto){
  return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(StockDto));
  /*  ResponseEntity nos ajuda a manipular a resposta, neste caso para retornar ]
  status 201 de criação de dados.} */




}
