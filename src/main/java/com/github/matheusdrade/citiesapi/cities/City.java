package com.github.matheusdrade.citiesapi.cities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.geo.Point;

@Entity
@Table(name = "cidade")
@TypeDefs(value = {
        @TypeDef(name = "point", typeClass = PointType.class)
})
public class City {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    private Integer uf;

    private Integer ibge;

    // 1st - trazendo as coordenadas por meio da string geolocation, no BD o tipo de dados é Point
    @Column(name = "lat_lon")
    private String geolocation;

    // 2nd - ensino ao Hibernate que o tipo dessa coluna é point, e ele corresponde a esse Point do Spring
    @Type(type = "point")
    @Column(name = "lat_lon", updatable = false, insertable = false)
    private Point location; //o Spring já tem um tipo de dados Point (q representa um ponto geoespacial x,y)

    public City() {
    }

    /*public City(final Long id, final String name, final Integer uf, final Integer ibge,
                final String geolocation, final Point location) {
        this.id = id;
        this.name = name;
        this.uf = uf;
        this.ibge = ibge;
        this.geolocation = geolocation;
        this.location = location;
    }*/

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public Point getLocation() {
        return location;
    }
}
