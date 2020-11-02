/*
---------------------------------------------------------------
Lucas
1. CRUD e tela de cadastro de planos - TELA CONCLUÍDA
- Métodos: 
  Dentro da pasta br.com.torrentz.model criar a classe "Plano" de acordo com o
     Modelo Conceitual

  DAL getAll, getById, getByName, search
  BLL add, update, validate
	
  getAll = Buscar todos
  getById = Buscar pelo ID do plano
  getByName = Buscar pelo nome do plano
  search = pesquisar um texto qualquer nos nomes dos planos
  BLL add, update, validate

   Pesquisa
   SELECT * FROM planos WHERE LOWER(pla_nome) LIKE %texto%;

2. Cronograma Resumido
3. Riscos
---------------------------------------------------------------
 */
package br.com.torrentz.dal;

import br.com.torrentz.model.Plano;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Computador
 */
public class DalPlano {
    
    
      //metodo getall
      public List<Plano> getall() {
        List<Plano> planos = new ArrayList<Plano>();
//        String sql = "select * from planos";
//        try {
//            Statement statement = .createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//            while (rs.next()) {
//                Plano plano = new Plano();//   tem_iden
//                plano.setPla_id(rs.getInt("pla_id"));
//                plano.setPla_nome(rs.getString("pla_nome"));
//                plano.setPla_acesso_simultaneo(rs.getInt("pla_acesso_simultaneo"));
//                plano.setPla_preco(rs.getFloat("pla_preco"));
//                planos.add(plano);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return planos;
    }
      
   
      
      //getById
      public Plano getByid(int pla_id) {
        Plano plano = new Plano();
//        try {
//            PreparedStatement preparedStatement = conexao.
//                    prepareStatement("select * from planos where pla_id=?");
//            preparedStatement.setInt(1, pla_id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                plano.setPla_id(rs.getInt("pla_id"));
//                plano.setPla_nome(rs.getString("pla_nome"));
//                plano.setPla_acesso_simultaneo(rs.getInt("pla_acesso_simultaneo"));
//                plano.setPla_preco(rs.getFloat("pla_preco"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return plano;
    }
      //getByName
}
