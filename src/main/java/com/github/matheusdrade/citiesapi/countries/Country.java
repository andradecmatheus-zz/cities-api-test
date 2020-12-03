package com.github.matheusdrade.citiesapi.countries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pais") //fala ao Spring que esta tabela se chama pais, mas a classe se chama Country
public class Country {

    @Id //fala pro JPA que esse atributo é a coluna ID do BD
    private Long id;

    @Column(name = "nome") //a coluna no BD se chama nome, mas aqui é name
    private String name;

    @Column(name = "nome_pt")
    private String portugueseName;

    @Column(name = "sigla")
    private String code;

    private Integer bacen; //qnd o nome da entity é o mesmo da coluna, ñ é preciso mapear

    public Country() {

    }

    public Country(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPortugueseName() {
        return portugueseName;
    }

    public String getCode() {
        return code;
    }

    public Integer getBacen() {
        return bacen;
    }
}

/*
    A entidade tem o nome no singular, o recurso rest são todos no plural (a rota é no plural).
    Aqui é mapeada todas as colunas do BD citties para essa entidade/classe aqui.
 */