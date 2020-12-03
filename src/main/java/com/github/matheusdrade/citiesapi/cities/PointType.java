package com.github.matheusdrade.citiesapi.cities;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.geometric.PGpoint;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PointType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] {java.sql.Types.OTHER};
    }

    @Override
    public Class returnedClass() {
        return Point.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session,
                              Object owner) throws HibernateException, SQLException {
    /* 1st
    Object object = rs.getObject(names[0]);
    Double[] points = StringLocationUtils.transform(object.toString());
    return new Point(points[0], points1);*/

        /* 2nd */
        PGpoint value = (PGpoint) rs.getObject(names[0]); // essa clase PGpoint é do Postgress
        return new Point(value.x, value.y); //import org.springframework.data.geo.Point;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
                            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {

    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return null;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return null;
    }
}

/*
        Esta classe PointType é a instrução p/ o Spring ensinar o Hibernate a mapear esse Ponto

        linha 43: // p/ poder reconhecer esse tipo PGpoint é preciso deixar o Postgress implementado:
            trocar runtimeOnly 'org.postgresql:postgresql' para implementation 'org.postgresql:postgresql'
            essa clase é do Postgress

 */