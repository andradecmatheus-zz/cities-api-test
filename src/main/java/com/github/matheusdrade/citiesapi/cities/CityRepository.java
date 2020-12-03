package com.github.matheusdrade.citiesapi.cities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query(value = "SELECT ((SELECT lat_lon FROM cidade WHERE id=?1) <@> (SELECT lat_lon FROM cidade WHERE id=?2)) as distance", nativeQuery = true)
    Double distanceByPoints(final Long cityId1, final Long cityId2);

    @Query(value = "SELECT earth_distance(ll_to_earth(?1,?2), ll_to_earth(?3,?4)) as distance", nativeQuery = true)
    Double distanceByCube(final Double lat1, final Double lon1, final Double lat2, final Double lon2);
}

/*
*       Aqui nesta interface estão declados dois métodos para fazer o cálculo, porém isso é insuficiente p/ o spring,
* ele ñ sabe o q tem que fazer. Por isso agr é anotar esses métodos como parâmetros e as queries q eles tem q fazer no BD
*
*       linhas 7 e 10 @Query: é a forma que falo ao spring sobre qual query vai ser executada.
*       Lembrando que por default esse método CityRepository já expoe vários métodos por causa da extensão JpaRepository,
* esses métodos são: o FindAll(), etc. adicionamos mais comportamento, colocando + dois métodos: distancia por pontos e
* distancia por cubo.
* */