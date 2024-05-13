package com.gilmar.spring_angular_backend.dto.dto_mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.gilmar.spring_angular_backend.dto.ProfitDto;
import com.gilmar.spring_angular_backend.dto.StockDto;
import com.gilmar.spring_angular_backend.enums.Category;
import com.gilmar.spring_angular_backend.model.Profit;
import com.gilmar.spring_angular_backend.model.Stock;


@Component
public class StockDtoMapper {

  //nos ajudará a trasformar model para dto para ser enviado para o cliente.
  public StockDto toDTO(Stock stock){
      if(stock!=null){

      //converter de profit para dtoProfit para poder utilizar no construtor de StockDto.
      List<ProfitDto> dtoProfit=stock.getProfits()
                      .stream()
                      .map(profit->new ProfitDto(profit.getId(), profit.getValueDistributed(), profit.getType(),profit.getStock()))
                      .collect(Collectors.toList());
      return new StockDto(stock.getId(), stock.getName(), stock.getPrice_medium(),stock.getCategory().getValue(),dtoProfit );
      }
      return null;
  }



  //nos ajudará a transformar dto para model para poder ser inserido no banco
  public Stock toStock(StockDto dto){
    if(dto!=null){
      Stock stock=new Stock();
      //essa lógica impede que se um id que já existe for enviado modifique os dados do mesmo.
     if(dto.id()==null){
      stock.setId(dto.id());
    }


      //aqui para baixo é sempre executado.
      stock.setName(dto.name());
      stock.setPrice_medium(dto.price_medium());
      stock.setCategory(stringToEnum(dto.category()));

      List<Profit> newProfit=dto.profits().stream().map(dtoProfit->{
        Profit profit=new Profit();
        var stockProfit=stock;
        stockProfit.setId(dto.id());
        profit.setId(dtoProfit.id());
        profit.setValueDistributed(dtoProfit.valueDistributed());
        profit.setType(dtoProfit.type());
        profit.setStock(stockProfit);
        return profit;
      } ).collect(Collectors.toList());

      stock.setProfits(newProfit);

      return stock;
    }
    return null;

  }


  //metodo para converter string do dto recebido para um enum valido para o model salvar
  public Category stringToEnum(String value){
   return switch (value) {
      case "geral" -> Category.GERAL;
      case "tech" -> Category.TECHNOLOGY;
      case "constr"-> Category.CONSTRUCTION;
      case "agrib"-> Category.AGRIBUSINESS;
      case "foodDrink"-> Category.FOOD_AND_DRINKS;
      case "educ"-> Category.EDUCATION;
      case "finance"-> Category.FINANCE;
      case "eletr"-> Category.ELETRIC;
      case "mall"-> Category.MALL;
      case "miniSteel"-> Category.MINING_AND_STEEL;
      default -> throw new IllegalArgumentException("Categoria inválida"+value);

    };
  }

  public ProfitDto toProfitDto(Profit profit){
    return new ProfitDto(profit.getId(), profit.getValueDistributed(), profit.getType(),profit.getStock());
  }


  public List<Profit> toProfit(List<ProfitDto> profitDto){
    List<Profit> profitList;
    var profit=new Profit();

    profitList=profitDto.stream().map((x->{
      profit.setId(x.id());
      profit.setStock(x.stock());
      profit.setType(x.type());
      profit.setValueDistributed(x.valueDistributed());
      return profit;
    })).collect(Collectors.toList());


    return profitList;
  }





}
