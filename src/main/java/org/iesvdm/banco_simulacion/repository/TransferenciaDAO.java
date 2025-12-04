package org.iesvdm.banco_simulacion.repository;

import org.iesvdm.banco_simulacion.dto.TransferenciaDTO;
import org.iesvdm.banco_simulacion.model.Cuenta;
import org.iesvdm.banco_simulacion.model.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TransferenciaDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTransferencia(Transferencia transferencia) {

        String sql = """
                insert into transferencia_programada(cuenta_origen_id, nombre_beneficiario, iban_destino, importe, concepto, fecha_programada, estado)
                values(?,?,?,?,?,?.?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] ids = {"id"};

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, ids);
            ps.setLong(1, transferencia.getCuentaId());
            ps.setString(2, transferencia.getNombreBeneficiario());
            ps.setString(3, transferencia.getIbanDestino());
            ps.setBigDecimal(4, transferencia.getImporte());
            ps.setString(5, transferencia.getConcepto());
            ps.setDate(6, Date.valueOf(transferencia.getFechaProgramada()));
            ps.setString(7, transferencia.getEstado());
            return ps;
        }, keyHolder);

        transferencia.setId(keyHolder.getKey().longValue());

    }


    public List<Transferencia> findAllTransferencia(){

        String sql = """
            SELECT * FROM transferencia_programada
            """;

        List<Transferencia> transferencias = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> Transferencia.builder()
                        .id(rs.getLong("id"))
                        .cuentaId(rs.getLong("cuenta_origen_id"))
                        .nombreBeneficiario(rs.getString("nombre_beneficiario"))
                        .ibanDestino(rs.getString("iban_destino"))
                        .fechaProgramada(rs.getDate("fecha_programada").toLocalDate())
                        .importe(rs.getBigDecimal("importe"))
                        .estado(rs.getString("estado"))
                        .build());

        return transferencias;

    }

    public Transferencia findById(Long id){

        String sql = "SELECT * FROM transferencia_programada WHERE id = ?";

        Transferencia transferencia = jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> Transferencia.builder()
                        .id(rs.getLong("id"))
                        .cuentaId(rs.getLong("cuenta_origen_id"))
                        .nombreBeneficiario(rs.getString("nombre_beneficiario"))
                        .ibanDestino(rs.getString("iban_destino"))
                        .importe(rs.getBigDecimal("importe"))
                        .concepto(rs.getString("concepto"))
                        .fechaProgramada(rs.getDate("fecha_programada").toLocalDate())
                        .fechaCreacion(rs.getDate("fecha_creacion").toLocalDate().atStartOfDay())
                        .estado(rs.getString("estado"))
                        .build()
                , id);

        return transferencia;

    }


    public void eliminarTransferencia (Long  id){

        int row = jdbcTemplate.update("""
            DELETE FROM transferencia_programada WHERE id = ?
        """, id);

    }


}
