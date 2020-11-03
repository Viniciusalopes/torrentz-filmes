package br.com.torrentz.model;

import java.util.Date;

/**
 *
 * @author vovolinux
 */
public class Usuario {

    private int id = 0;
    private char perfil = 'U';
    private String nome = "";
    private String cpf = "";
    private String email = "";
    private String senha = "";
    private float porcentagem = 0;
    private Date dataGeracao = new Date();

    public Usuario() {

    }

    public Usuario(int id, String nome, String cpf, String email, String senha, float porcentagem,
            Date dataGeracao, char perfil) throws Exception {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.porcentagem = porcentagem;
        this.dataGeracao = dataGeracao;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public float getPorcentagem() {
        return porcentagem;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public char getPerfil() {
        return perfil;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPorcentagem(float porcentagem) {
        this.porcentagem = porcentagem;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public void setPerfil(char perfil) {
        this.perfil = perfil;
    }

}
