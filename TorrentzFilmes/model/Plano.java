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
    private float pla_preço;

    //Atributos com valores em int
    private int pla_acesso_simultaneo;
    private int pls_id;
    //-----------------------------------------------------------------------------------------------

    //contrutor vazio 
    public Plano() {

    }

    //----------------------------------------------------------------------------------------------
    //contrutor com parametros
    public Plano(String pla_nome, float pla_preço, int pla_acesso_simultaneo, int pls_id) {
        this.pla_nome = pla_nome;
        this.pla_preço = pla_preço;
        this.pla_acesso_simultaneo = pla_acesso_simultaneo;
        this.pls_id = pls_id;
    }

    //--------------------------------------------------------------------------------------------------
    //geter's

    public String getPla_nome() {
        return pla_nome;
    }

    public float getPla_preço() {
        return pla_preço;
    }

    public int getPla_acesso_simultaneo() {
        return pla_acesso_simultaneo;
    }

    public int getPls_id() {
        return pls_id;
    }
   

    //---------------------------------------------------------------------------------------------
    //setr's
    public void setPla_nome(String pla_nome) {
        this.pla_nome = pla_nome;
    }

    public void setPla_preço(float pla_preço) {
        this.pla_preço = pla_preço;
    }

    public void setPla_acesso_simultaneo(int pla_acesso_simultaneo) {
        this.pla_acesso_simultaneo = pla_acesso_simultaneo;
    }

    public void setPls_id(int pls_id) {
        this.pls_id = pls_id;
    }
    //--------------------------------------------------------------------------------------

    
}
