package com.github.matheusdrade.citiesapi.countries.repository;

import com.github.matheusdrade.citiesapi.countries.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}

/*
        Esta interface extend o JpaRepository, passando o tipo da entidade que é um Country, e o tipo do identificado
da entidade, Long.
        Um  a classe que representa o repositório do meu pais foi criada. Com isso o Springdata já expoe o metodo finAll,
q retorna a lista dos paises. Esta é a mágica do framework SpringData que já provê uma implementação para todas as
operações básicas de BD.
 */