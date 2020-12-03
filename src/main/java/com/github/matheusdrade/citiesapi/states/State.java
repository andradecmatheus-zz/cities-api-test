package com.github.matheusdrade.citiesapi.states;

import com.github.matheusdrade.citiesapi.countries.Country;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Entity(name = "State")
@Table(name = "estado")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class State {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    private String uf;

    private Integer ibge;

  /*// 1st - traz o ID
  @Column(name = "pais")
  private Integer countryId;*/

    // 2nd - @ManyToOne - traz o Nome do Pais referente a tal ID
    @ManyToOne //muitos estados para um pais
    @JoinColumn(name = "pais", referencedColumnName = "id") //coluna q referencia os dois está na tabela pais, atributo id
    private Country country;

    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ddd", columnDefinition = "jsonb")
    private List<Integer> ddd;

    public State() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public List<Integer> getDdd() {
        return ddd;
    }

    public Country getCountry() {
        return country;
    }

  /*public Integer getCountryId() {
      return countryId;
  }*/
}

/*
Como falar ao código p/ trazer uma lista de array? Por exemplo, o atributo DDD de estados é um array.
O Hibernate não sabe fazer sozinho, o tipo de dados do banco de ddd é json, isso não é naturalmente hibernate (q é o q
implementa o JPA-especificação), então temos q ensinar ele a mapear aquele json em uma lista de inteiros.
Para isso vamos utilizar uma biblioteca de terceiros -> "implementation 'com.vladmihalcea:hibernate-types-52:2.9.8'"
	- então coloca-se essa dependencia no projeto.Ao mandar o gradle atualizar, ele já baixa.
Agora em State é falado ao Hibernate (TypeDefs) que a coluna "jsonb" está dentro da dependencia recém instalada.
	- então a dependencia foi colocada no projeto para ensinar o hibernate a trabalhar com o tipo json
com isso é falado ao hibernate q qnd for fazer um select na tabela de Estado, os datalists ddd .....
 */