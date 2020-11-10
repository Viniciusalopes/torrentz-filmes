/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.dal;

import br.com.torrentz.generic.BDConn;
import br.com.torrentz.model.Categoria;
import br.com.torrentz.model.Filme;
import br.com.torrentz.model.Usuario;
import br.com.torrentz.model.Visualizacao;
import br.com.torrentz.util.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author marcos
 */
public abstract class DalVisualizacao {
    private Connection conn = null;
    
    public DalVisualizacao() throws Exception{
        BDConn conexao = BDConn.getInstance();
        conn = conexao.getConnection();
    }
    public ArrayList<Visualizacao> getAll() throws SQLException, Exception{
        String sql = "SELECT *  FROM visualizacoes " +
                     "JOIN usuarios ON visualizacoes.vis_usu_id = usuarios.usu_id " +
                     "JOIN filmes ON visualizacoes.vis_fil_id = filmes.fil_id " +
                     "JOIN categorias ON filmes.fil_cat_id = categorias.cat_id ";
                    
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
          
            ArrayList<Visualizacao> list = new ArrayList<>();
            while(rs.next()){
                list.add(new Visualizacao(
                        new Data(rs.getString("vis_data")),
                        rs.getBoolean("vis_completo"),
                        new Usuario(
                            rs.getInt("usu_id"),
                            rs.getString("usu_nome"),
                            rs.getString("usu_cpf"),
                            rs.getString("usu_email"),
                            rs.getString("usu_senha"),
                            rs.getFloat("usu_cup_porcentagem"),
                            rs.getDate("usu_cup_data_geracao"),
                            rs.getString("usu_perfil").charAt(0)),
                        new Filme(
                            rs.getInt("fil_id"),
                            rs.getString("fil_titulo"),
                            rs.getString("fil_sinopse"),
                            rs.getInt("fil_ano"),
                            new Categoria(
                                    rs.getInt("cat_id"),
                                    rs.getString("cat_nome"))
                        ))
                );
            }
            return list;
        } catch (SQLException error) {
            throw error;
        }
    }
    public void configura(Visualizacao visualizacao) throws Exception{
        try {
            if(verificaSeExiste(visualizacao.getUsuario().getId(), visualizacao.getFilme().getId())){
                atualiza(visualizacao);
            }else{
                adicionar(visualizacao);
            }            
        } catch (SQLException error) {
            throw error;
        }
    }
    private void atualiza(Visualizacao visualizacao) throws Exception{
        String data =  visualizacao.getData().get().replace("-", "/");
        String sql = "UPDATE visualizacoes SET vis_data = TO_DATE('"+data+"', 'DD/MM/YYYY'), vis_completo = ?, vis_usu_id = ?, vis_fil_id = ? WHERE vis_usu_id = ? AND vis_fil_id = ? ";
        try {
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, visualizacao.isCompleto());
                pstm.setInt(2, visualizacao.getUsuario().getId());
                pstm.setInt(3, visualizacao.getFilme().getId());
                pstm.setInt(4, visualizacao.getUsuario().getId());
                pstm.setInt(5, visualizacao.getFilme().getId());
                pstm.executeUpdate();
           
        } catch (SQLException error) {
            throw error;
        }
    }
    public ArrayList<Visualizacao> buscaPorUsuario(Usuario usuario) throws SQLException, Exception{
    String sql = "SELECT *  FROM visualizacoes " +
                     "JOIN usuarios ON visualizacoes.vis_usu_id = usuarios.usu_id " +
                     "JOIN filmes ON visualizacoes.vis_fil_id = filmes.fil_id " +
                     "JOIN categorias ON filmes.fil_cat_id = categorias.cat_id " +
                     "WHERE vis_usu_id = ?";
                    
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, usuario.getId());
            ResultSet rs = pstm.executeQuery();
          
            ArrayList<Visualizacao> list = new ArrayList<>();
            while(rs.next()){
                list.add(new Visualizacao(
                        new Data(rs.getString("vis_data")),
                        rs.getBoolean("vis_completo"),
                        new Usuario(
                            rs.getInt("usu_id"),
                            rs.getString("usu_nome"),
                            rs.getString("usu_cpf"),
                            rs.getString("usu_email"),
                            rs.getString("usu_senha"),
                            rs.getFloat("usu_cup_porcentagem"),
                            rs.getDate("usu_cup_data_geracao"),
                            rs.getString("usu_perfil").charAt(0)),
                        new Filme(
                            rs.getInt("fil_id"),
                            rs.getString("fil_titulo"),
                            rs.getString("fil_sinopse"),
                            rs.getInt("fil_ano"),
                            new Categoria(
                                    rs.getInt("cat_id"),
                                    rs.getString("cat_nome"))
                        ))
                );
            }
            return list;
        } catch (SQLException error) {
            throw error;
        }
    }
    private void adicionar(Visualizacao visualizacao) throws Exception{
        String data =  visualizacao.getData().get().replace("-", "/");
        String sql = "INSERT INTO visualizacoes (vis_data, vis_completo, vis_usu_id, vis_fil_id) VALUES(TO_DATE('"+data+"', 'DD/MM/YYYY'),?,?,?)";
        try {
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, visualizacao.isCompleto());
                pstm.setInt(2, visualizacao.getUsuario().getId());
                pstm.setInt(3, visualizacao.getFilme().getId());
                pstm.executeUpdate();
           
        } catch (SQLException error) {
            throw error;
        }
    }
    public boolean verificaSeExiste(int usuarioId,int filmeId) throws Exception{
        String sql = "SELECT COUNT(*)  FROM visualizacoes " +
                "JOIN usuarios ON visualizacoes.vis_usu_id = usuarios.usu_id " +
                "JOIN filmes ON visualizacoes.vis_fil_id = filmes.fil_id " +
                "WHERE usuarios.usu_id = ? AND filmes.fil_id = ? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, usuarioId);
            pstm.setInt(2, filmeId);
            ResultSet rs = pstm.executeQuery();
            int qnt = 0;
            
            if(rs.next()){
                qnt = rs.getInt(1);
            }
            return qnt != 0;
        } catch (SQLException error) {
            throw error;
        }
    } 
        
   
}
