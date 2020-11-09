package br.com.torrentz.bll;

import br.com.torrentz.dal.DalUsuario;
import br.com.torrentz.model.Usuario;
import static br.com.torrentz.util.UtilSenha.getHexStringSha256;
import static br.com.torrentz.util.UtilString.somenteNumeros;
import java.util.ArrayList;

/**
 *
 * @author vovolinux
 */
public class BllUsuario extends DalUsuario {

    public BllUsuario() throws Exception {
        super();
    }

    public void validate(Usuario usuario) throws Exception {
        usuario.setSenha(validatedPassword(usuario.getSenha()));
    }

    public String validatedPassword(String password) throws Exception {
        int tamanho = password.trim().length();
        if (tamanho < 2) {
            throw new Exception("Senha muito curta!");
        }
        if (tamanho > super.getMaxLength("usu_senha")) {
            throw new Exception("Senha muito longa!");
        }
        if (password.equals("123")) {
            throw new Exception("Senha da Nasa não é permitida!");
        }
        return getHexStringSha256(password);
    }

    @Override
    public void add(Usuario usuario) throws Exception {
        validate(usuario);
        super.add(usuario);
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        validate(usuario);
        super.update(usuario);
    }

    public Usuario validUser(String login, String password) throws Exception {
        Usuario ret = super.getByLoginPassword(login, password);
        if (ret == null) {
            throw new Exception("Usuário ou senha inválidos!");
        }
        return ret;
    }

    public boolean isAdmin(String login, String password) throws Exception {
        return validUser(login, password).getPerfil() == 'A';
    }

    public Usuario searchByLogin(String login) throws Exception {
        ArrayList<Usuario> ret = search(login);
        return (ret.size() > 0) ? ret.get(0) : null;
    }

    public Usuario searchByCPF(String cpf) throws Exception {
        ArrayList<Usuario> ret = search(cpf);
        return (ret.size() > 0) ? ret.get(0) : null;
    }
}
