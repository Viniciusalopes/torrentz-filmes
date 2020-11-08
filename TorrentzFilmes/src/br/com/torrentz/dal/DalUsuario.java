
package br.com.torrentz.dal;

import br.com.torrentz.generic.Where;
import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.model.Usuario;
import static br.com.torrentz.util.UtilSenha.getHexStringSha256;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author vovolinux
 */
public abstract class DalUsuario extends DalGeneric<Usuario> {

    protected DalUsuario() throws Exception {
        super("usuarios", "usu_id");

        sqlInsert = "INSERT INTO " + table
                + " (usu_nome, usu_cpf, usu_email, usu_senha, usu_cup_porcentagem, usu_cup_data_geracao, usu_perfil) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ";

        sqlSelect = "SELECT * FROM " + table + " ";

        sqlUpdate = " UPDATE " + table + " SET "
                + "usu_nome = ?, usu_cpf = ?, usu_email = ?, usu_senha = ?, usu_cup_porcentagem = ?, "
                + "usu_cup_data_geracao = ?, usu_perfil = ? " + sqlWhere;
        
        orderBy = " ORDER BY usu_nome";
    }

    @Override
    protected ArrayList<Usuario> build(ResultSet rs) throws Exception  {
        ArrayList<Usuario> ret = new ArrayList<>();
        while (rs.next()) {
            ret.add(new Usuario(
                    rs.getInt(fieldPK),
                    rs.getString("usu_nome"),
                    rs.getString("usu_cpf"),
                    rs.getString("usu_email"),
                    rs.getString("usu_senha"),
                    rs.getFloat("usu_cup_porcentagem"),
                    rs.getDate("usu_cup_data_geracao"),
                    rs.getString("usu_perfil").charAt(0)
            ));
        }
        return ret;
    }

    public Usuario getById(int id) throws Exception {
        sql = sqlSelect + sqlWhere;
        args = new Object[]{id};
        ArrayList<Usuario> ret = select();
        if (ret.size() == 0) {
            throw new Exception("Nenhum usuário cadastrado com o id [" + id + "] !");
        }
        return ret.get(0);
    }

    protected ArrayList<Usuario> getBy(Where[] where) throws Exception {
        sql = sqlSelect;
        return getByFields(where);
    }

    protected Usuario getByLoginPassword(String login, String password) throws Exception{
        ArrayList<Usuario> consulta = getBy(new Where[]{
            new Where("", "usu_cpf", "=", login),
            new Where("OR", "usu_email", "=", login)
        });
        for (Usuario usuario : consulta) {
            if(usuario.getSenha().equals(getHexStringSha256(password)))
                return usuario;
        }
        return null;
    }
    
    public ArrayList<Usuario> search(String text) throws Exception {
        String t = "%" + text.toLowerCase().trim() + "%";
        sql = sqlSelect
                + " WHERE LOWER(usu_nome) LIKE ? "
                + "   OR usu_cpf LIKE ?"
                + "   OR LOWER(usu_email) LIKE ? "
                + orderBy;
        args = new Object[]{t, t, t};
        return select();
    }

    protected void add(Usuario usuario) throws Exception {
        args = new Object[]{
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getPorcentagem(),
            usuario.getDataGeracao(),
            usuario.getPerfil()
        };
        sql = sqlInsert;
        execute();
    }

    protected void update(Usuario usuario) throws Exception {
        args = new Object[]{
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getPorcentagem(),
            usuario.getDataGeracao(),
            usuario.getPerfil(),
            usuario.getId()
        };
        sql = sqlUpdate;
        execute();
    }
}
