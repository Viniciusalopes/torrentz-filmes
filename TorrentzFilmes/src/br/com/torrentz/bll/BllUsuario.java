package br.com.torrentz.bll;

import br.com.torrentz.dal.DalUsuario;
import br.com.torrentz.model.Usuario;

/**
 *
 * @author vovolinux
 */
public class BllUsuario extends DalUsuario {

    public BllUsuario() throws Exception {
        super();
    }

    public void validate(Usuario usuario) throws Exception {

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
}
