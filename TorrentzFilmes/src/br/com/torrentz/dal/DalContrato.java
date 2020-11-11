/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.dal;

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.generic.Where;
import br.com.torrentz.model.Contrato;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author vovolinux
 */
public class DalContrato extends DalGeneric<Contrato> {

    public DalContrato() throws Exception {
        super("contratos", "con_usu_id, con_pla_id");
        sqlInsert = "INSERT INTO Contratos (con_status, con_inicio, con_fim, con_usu_id, con_pla_id) "
                + "VALUES (?, ?, ?, ?, ?) ";

        sqlSelect = "SELECT * FROM " + table + " ";

        sqlWhere = " WHERE con_usu_id = ? AND con_pla_id = ?";

        sqlUpdate = " UPDATE " + table + " SET "
                + "con_status = ?, con_inicio = ?, con_fim = ? " + sqlWhere;

        orderBy = " ORDER BY con_inicio DESC";
    }

    @Override
    protected ArrayList<Contrato> build(ResultSet rs) throws Exception {
        ArrayList<Contrato> ret = new ArrayList<>();
        while (rs.next()) {
            ret.add(new Contrato(
                    rs.getString("con_status").charAt(0),
                    rs.getDate("con_inicio"),
                    rs.getDate("con_fim"),
                    rs.getInt("con_usu_id"),
                    rs.getInt("con_pla_id")
            ));
        }
        return ret;
    }

    public Contrato getById(int idUsuario, int idPlano) throws Exception {
        sql = sqlSelect + sqlWhere;
        args = new Object[]{idUsuario, idPlano};
        ArrayList<Contrato> ret = select();
        if (ret.size() == 0) {
            throw new Exception("Nenhum contratousuário cadastrado para o usuário [" + idUsuario + "] !");
        }
        return ret.get(0);
    }

    public ArrayList<Contrato> getByUsuarioId(int id) throws Exception {
        sql = sqlSelect + " WHERE con_usu_id = ? " + orderBy;
        args = new Object[]{id};
        return select();
    }

    protected ArrayList<Contrato> getBy(Where[] where) throws Exception {
        sql = sqlSelect;
        return getByFields(where);
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

    protected void update(Contrato contrato) throws Exception {
        sql = sqlUpdate;
        args = new Object[]{
            contrato.getStatus(),
            contrato.getInicio(),
            contrato.getFim(),
            contrato.getUsuarioId(),
            contrato.getPlanoId()
        };
        execute();
    }

}
