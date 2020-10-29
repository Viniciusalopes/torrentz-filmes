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
    private String NomeDoPlano;
    private float PreçoPlano;

    //Atributos com valores em int
    private int AcesoSimultaneoPlano;
    private int PlanoId;
    //-----------------------------------------------------------------------------------------------

    //contrutor vazio 
    public Plano() {

    }

    //----------------------------------------------------------------------------------------------
    //contrutor com parametros
    public Plano(String StausDoPlano, String NomeDoPlano, float PreçoPlano, int AcesoSimultaneoPlano, int PlanoId) {

        this.NomeDoPlano = NomeDoPlano;
        this.PreçoPlano = PreçoPlano;
        this.AcesoSimultaneoPlano = AcesoSimultaneoPlano;
        this.PlanoId = PlanoId;
    }
    //--------------------------------------------------------------------------------------------------

    //geter's
    public String getNomeDoPlano() {
        return NomeDoPlano;
    }

    public float getPreçoPlano() {
        return PreçoPlano;
    }

    public int getAcesoSimultaneoPlano() {
        return AcesoSimultaneoPlano;
    }

    public int getPlanoId() {
        return PlanoId;
    }

    //---------------------------------------------------------------------------------------------
    //setr's
    public void setNomeDoPlano(String NomeDoPlano) {
        this.NomeDoPlano = NomeDoPlano;
    }

    public void setPreçoPlano(float PreçoPlano) {
        this.PreçoPlano = PreçoPlano;
    }

    public void setAcesoSimultaneoPlano(int AcesoSimultaneoPlano) {
        this.AcesoSimultaneoPlano = AcesoSimultaneoPlano;
    }

    public void setPlanoId(int PlanoId) {
        this.PlanoId = PlanoId;
    }

    //--------------------------------------------------------------------------------------
}
