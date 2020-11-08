/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.dal;

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.model.Contrato;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author vovolinux
 */
public class DalContrato extends DalGeneric<Contrato> {

    public DalContrato() throws Exception {
        super("Contratos", "con_usu_id, con_pla_id");
        sqlInsert = "INSERT INTO Contratos (con_status, con_inicio, con_fim, con_usu_id, con_pla_id) "
                + "VALUES (?, ?, ?, ?, ?) ";
    }

    protected void add(Contrato contrato) throws Exception {
        sql = sqlInsert;
        args = new Object[]{
            contrato.getStatus(),
            contrato.getInicio(),
            contrato.getFim(),
            contrato.getUsuarioId(),
            contrato.getPlanoId()
        };
        execute();
    }

    @Override
    protected ArrayList<Contrato> build(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
