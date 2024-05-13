package com.gilmar.spring_angular_backend;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gilmar.spring_angular_backend.enums.Category;
import com.gilmar.spring_angular_backend.enums.TypeProfit;
import com.gilmar.spring_angular_backend.model.Profit;
import com.gilmar.spring_angular_backend.model.Stock;
import com.gilmar.spring_angular_backend.repository.RepositoryStock;



@SpringBootApplication
public class SpringAngularBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularBackendApplication.class, args);
	}


  //Bean para criar uma aplicação a parte para gerar um banco de dados(repositorio) para testes.
  @Bean
  CommandLineRunner bancoDados(RepositoryStock stockRepository){
    return arg->{
        stockRepository.deleteAll();
     for(int i=0;i<20;i++){
        //primeiro dado
        Stock stock=new Stock();
        stock.setName("Valid");
        stock.setPrice_medium(14.5);
        stock.setCategory(Category.TECHNOLOGY);

        Profit profit=new Profit();
        profit.setStock(stock);
        profit.setType(TypeProfit.DY);
        profit.setValueDistributed(2.3);
        Profit profit2=new Profit();
        profit2.setStock(stock);
        profit2.setType(TypeProfit.JCP);
        profit2.setValueDistributed(5.4);
        stock.getProfits().add(profit);
        stock.getProfits().add(profit2);

        //segundo dado
        Stock stock2=new Stock();
        stock2.setName("Jhsf");
        stock2.setCategory(Category.GERAL);
        stock2.setPrice_medium(7.85);

        stockRepository.save(stock);
        stockRepository.save(stock2);
     }

    };
  }

}
