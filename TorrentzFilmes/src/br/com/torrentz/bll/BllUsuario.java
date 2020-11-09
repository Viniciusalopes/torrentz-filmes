package br.com.torrentz.bll;

import br.com.torrentz.dal.DalUsuario;
import br.com.torrentz.model.Usuario;
import br.com.torrentz.util.CpfValidation;
import br.com.torrentz.util.UtilEmail;
import static br.com.torrentz.util.UtilSenha.getHexStringSha256;
import static br.com.torrentz.util.UtilString.soTemLetras;
import static br.com.torrentz.util.UtilString.textoSoComNumeros;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author vovolinux
 */
public class BllUsuario extends DalUsuario {

    public BllUsuario() throws Exception {
        super();
    }

    public void validate(Usuario usuario) throws Exception {
        if (!soTemLetras(usuario.getNome())) {
            throw new Exception("O nome possui caracteres inválidos!");
        }
        if (!CpfValidation.isValid(usuario.getCpf())) {
            throw new Exception("O CPF é inválido!");
        }
        usuario.setCpf(textoSoComNumeros(usuario.getCpf()));
        
        if (!UtilEmail.isValid(usuario.getEmail())) {
            throw new Exception("O e-mail é inválido!");
        }
        if (usuario.getDataGeracao().getTime() > new Date().getTime()) {
            throw new Exception("A data de início não pode ser maior que a data atual!");
        }

        usuario.setSenha(validatedPassword(usuario.getSenha()));
    }

    public void alreadyExists(Usuario usuario, Iterable<Usuario> usuarios) throws Exception {
        for (Usuario u : usuarios) {
            if (u.getId() != usuario.getId()) {
                if (u.getCpf().equals(usuario.getCpf())) {
                    throw new Exception("Já existe um usuário cadastrado com esse CPF");
                }
                if (u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                    throw new Exception("Já existe um usuário cadastrado com esse E-mail");
                }
            }
        }
    }

    public String validatedPassword(String password) throws Exception {
        int tamanho = password.trim().length();
        if (tamanho < 2) {
            throw new Exception("A senha muito curta!");
        }
        if (tamanho > super.getMaxLength("usu_senha")) {
            throw new Exception("A senha muito longa!");
        }
        if (password.equals("123")) {
            throw new Exception("A senha da Nasa não é permitida!");
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
        ArrayList<Usuario> ret = search(textoSoComNumeros(cpf));
        return (ret.size() > 0) ? ret.get(0) : null;
    }
}
