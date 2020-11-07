/*

 */
package br.com.torrentz.model;

/**
 *
 * @author Lucas Araujo
 *
 */
public class Plano {

    //----------------------------------------------------------------------------------------------
    //atributos 
    //atributos com valores em String
    private String pla_nome;
    
    //atrbutos em float
    private float pla_preco;

    //Atributos com valores em int
    private int pla_acesso_simultaneo;
    private int pla_id;
    //-----------------------------------------------------------------------------------------------

    //contrutor vazio 
    public Plano() {

    }

    //----------------------------------------------------------------------------------------------
    //contrutor com parametros
    public Plano(int pla_id, int pla_acesso_simultaneo, String pla_nome, float pla_preco ) {
        this.pla_id = pla_id;
        this.pla_acesso_simultaneo = pla_acesso_simultaneo;
        this.pla_nome = pla_nome;
        this.pla_preco = pla_preco;
    }

    //--------------------------------------------------------------------------------------------------
    //geter's
    public String getPla_nome() {
        return pla_nome;
    }

    public float getPla_preco() {
        return pla_preco;
    }

    public int getPla_acesso_simultaneo() {
        return pla_acesso_simultaneo;
    }

    public int getPla_id() {
        return pla_id;
    }

    //---------------------------------------------------------------------------------------------
    //setr's
    public void setPla_nome(String pla_nome) {
        this.pla_nome = pla_nome;
    }

    public void setPla_preco(float pla_preco) {
        this.pla_preco = pla_preco;
    }

    public void setPla_acesso_simultaneo(int pla_acesso_simultaneo) {
        this.pla_acesso_simultaneo = pla_acesso_simultaneo;
    }

    public void setPla_id(int pla_id) {
        this.pla_id = pla_id;
    }

    //--------------------------------------------------------------------------------------
}
