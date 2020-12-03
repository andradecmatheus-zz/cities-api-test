package com.github.matheusdrade.citiesapi.countries;

import com.github.matheusdrade.citiesapi.countries.Country;
import com.github.matheusdrade.citiesapi.countries.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryResource {

    //@Autowired //é preciso mandar ao Spring injetar. INJEÇÃO DE DEPENDENCIA.
    private CountryRepository repository; //cria-se uma interface dentro de .repository

    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

    // é escrito um método public q retorna uma Lista de Pais. Este método recebe o nome de countries, e retornará algo
    @GetMapping //informando ao Spring que esse método é um Get, verbos do protocolo HTTP
        public Page<Country> countries(Pageable page){ //import org.springframework.data.domain.Pageable;
        return repository.findAll(page); // p/ a API, p/ o projeto JAVA, o BD é um repositório de dados. > create with field
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Country> optional = repository.findById(id); //o findById retorna sempre um optional

        if (optional.isPresent()){
            return ResponseEntity.ok().body(optional.get()); //se o ResponseEntity existir, retornará algum optional
        } else {
            return ResponseEntity.notFound().build(); //se ñ existir, ñ retornará nada e com o Status code de 404 (ñ mas 500)
        }


    }

}
/*
        12 @GetMapping: tô falando ao Spring que tô fazendo um Get nessa rota/resource "/countries", q devolva a lisa de
 countries, por isso foi criada a classe Country (alt+enter > criar classe), q gerou automaticamente a pasta countries.
        16 @Autowired: pring injeta esse repositorio aqui para eu funcionar
 */
