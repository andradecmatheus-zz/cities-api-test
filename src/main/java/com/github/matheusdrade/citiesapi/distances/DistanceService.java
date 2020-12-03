package com.github.matheusdrade.citiesapi.distances;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;


/*import com.github.andrelugomes.cities.entities.City;
import com.github.andrelugomes.cities.repositories.CityRepository;
import com.github.andrelugomes.utils.StringLocationUtils;*/
import java.util.Arrays;
import java.util.List;

import com.github.matheusdrade.citiesapi.cities.City;
import com.github.matheusdrade.citiesapi.cities.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceService { //temos um serviço com dois métodos, p

    private final CityRepository cityRepository;
    Logger log = LoggerFactory.getLogger(DistanceService.class);

    public DistanceService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // 1o método: calcula a distância em milhas
    public Double distanceByPointsInMiles(final Long city1, final Long city2) { //recebe o id das cidades
        log.info("nativePostgresInMiles({}, {})", city1, city2);
        return cityRepository.distanceByPoints(city1, city2); // faz a conta
    }

    // 2o método: calcula a distância em metros
    public Double distanceByCubeInMeters(Long city1, Long city2) {
        log.info("distanceByCubeInMeters({}, {})", city1, city2);
        final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2))); //to get the others atribues by ID

        Point p1 = cities.get(0).getLocation();
        Point p2 = cities.get(1).getLocation();

        return cityRepository.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

}

/*
*       Para os dois métodos é necessário criar métodos no repository que vão executar as querys.
*       Quem executa as querys é o repository. E o city repository está só como interface, e nela declara dois métodos
* q vão * fazer esse cálculo.
*
*       by-points //resource
*       from e to //são parâmetros
*
*       by-points e by-cube ñ seguem o padrão restfull, esses nomes são para ilustrar/ensinar. No caso, o certo seria
* /distances?from=4929&&to=5254 pq numa aplicação usaria só um método e aq estamos usando dois para ilustrar.
*
*
*
* */