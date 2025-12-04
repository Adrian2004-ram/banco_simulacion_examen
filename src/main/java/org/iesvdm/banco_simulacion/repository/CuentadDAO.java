package org.iesvdm.banco_simulacion.repository;

import org.iesvdm.banco_simulacion.model.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CuentadDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Cuenta> findAllCuentas(){

        String sql = """
            SELECT * FROM cuenta_origen
            """;

        List<Cuenta> eventos = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> Cuenta.builder()
                        .id(rs.getLong("id"))
                        .aliasCuenta(rs.getString("alias_cuenta"))
                        .iban(rs.getString("iban"))
                        .build());

        return eventos;

    }

    public Cuenta findById(Long id){

        String sql = "SELECT * FROM cuenta_origen WHERE id = ?";

        Cuenta cuenta = jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> Cuenta.builder()
                        .id(rs.getLong("id"))
                        .aliasCuenta(rs.getString("alias_cuenta"))
                        .iban(rs.getString("iban"))
                        .build()
                , id);

        return cuenta;

    }

}
