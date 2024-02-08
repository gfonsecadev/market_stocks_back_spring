package com.gilmar.spring_angular_backend.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.gilmar.spring_angular_backend.enums.Category;
import com.gilmar.spring_angular_backend.enums.converters.CategoryConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Where(clause = "show=true")//Modifica o que o where buscará
@SQLDelete(sql="UPDATE Stock set show=false where id=?")//uma sql muda o que a instrução faria nativamente.
@Getter //anotação do Lombok para criar os gets
@Setter //anotação do Lombok para criar os sets
@Entity // anotação para criar uma entidade no banco de dados
@Table(name = "Stock")// serve para nomear a tabela se for diferente do nome da classe
public class Stock {

/*@JsonIgnore // não leva essa variavel para resposta no json, anotação do jackson
  @JsonProperty("id")// para mandar o atributo com esse nome no json(resposta), anotação do jackson */
  @Id // anotação para id no jpa
  @GeneratedValue(strategy = GenerationType.AUTO)// para gerar chave primaria
  private Long id;

  @NotBlank
  @NotNull
  @Length(min = 3,max = 100)
  @Column(name = "name",length = 20 ,nullable = false)// anotação para colunas(se o atributo for o mesmo do seu banco não tem necessidade colocar esta anotação)
  private String name;


  @NotNull
  @DecimalMin(value="0.1", message = "Value not allowed")//mensagem é personalizavel
  @Column(length = 20 , nullable = false )
  private Double price_medium;

 // @NotBlank//Não pode ser valor vazio
  @NotNull//Não pode ser nulo
  @Convert(converter = CategoryConverter.class)
  private Category category;

  @Column(length = 20, nullable=false)
  private Boolean show= true;

  //coluna de lucros distribuidos
  //uma ação pode ter várias lucros distribuidos
  @OneToMany(cascade = CascadeType.ALL , orphanRemoval=true,mappedBy="stock")
  @Column
  private List<Profit> profits=new ArrayList<>();

}
