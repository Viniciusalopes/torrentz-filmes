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

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.generic.Where;
import br.com.torrentz.model.Plano;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Computador
 */
public class DalPlano extends DalGeneric<Plano> {

    //contrutor protegido 
    /**
     *
     * O modificador protected torna o membro acessível às classes do mesmo
     * pacote ou através de herança, seus membros herdados não são acessíveis a
     * outras classes fora do pacote em que foram declarados.
     */
    protected DalPlano() throws Exception {
        super("planos ", "pla_id");

        sqlInsert = "INSERT INTO " + table
                + "(pla_id, pla_acesso_simultaneo, pla_nome, pla_preco"
                + "VALUES (?, ?, ?, ?)";

        sqlSelect = "SELECT * FROM " + table + " ";

        sqlUpdate = " UPDATE " + table + " SET "
                + "pla_acesso_simultaneo = ?, pla_nome = ?, pla_preco " + sqlWhere;

        orderBy = " ORDER BY pla_nome";

    }

    @Override //array list palno 
    protected ArrayList<Plano> build(ResultSet rs) throws Exception {
        ArrayList<Plano> ret = new ArrayList<>();
        while (rs.next()) {
            ret.add(new Plano(
                    rs.getInt(fieldPK),
                    rs.getInt("pla_acesso_simultaneo"),
                    rs.getString("pla_nome"),
                    rs.getFloat("pla_preco")
            ));

        }
        return ret;
    }//colado na orden de atributos da tabela 

    public Plano getById(int id) throws Exception {
        sql = sqlSelect + sqlWhere;
        args = new Object[]{id};

        ArrayList<Plano> ret = select();
        if (ret.size() == 0) {
            throw new Exception("Nenhum plano cadastrado com o id [ " + id + "] !");
        }
        return ret.get(0);

    }

    /*
    protected ListaMatriz<Plano> peguePor(Onde[] onde) lança 
Exceção {

        sql = sqlSelect;

        retornaPeguePorCampo(onde);

    ddd }
     */
    protected ArrayList<Plano> getBy(Where[] where) throws Exception {

        sql = sqlSelect;

        return getByFields(where);

    }


    /*
    Eu estou com um problema para entender o 
    ArrayList e a aplicação de seus métodos. 
    em especial o alcanse do ArrayList.contains()
    Eu tenho uma estrutura de dados que possui
    alguns ArrayLists (objetodaminhaclasse).
    esse (objetodaminhaclasse) possui um nome 
    (static int) para cada objeto que for criado poder ser 
    identificado. E a estrutura de dados possui um metodo 
    Search que busca dentro da "ArrayList(objetodaminhaclasse)
    “um” (objetodaminhaclasse) através de seu nome. ou seja:  
  
     */
    public ArrayList<Plano> search(String text) throws Exception {
        String t = "%" + text.toLowerCase().trim() + "%";
        sql = sqlSelect
                + "WHERE LOVER(  pla_acesso_simultaneo LIKE ?"
                + "  OR pla_nome LIKE ?"
                + "OR LOWER pla_preco LIKE ? "
                + orderBy;
        args = new Object[]{t, t, t};
        return select();

    }

    protected void add(Plano plano) throws Exception {
        args = new Object[]{
            plano.getPla_id(),
            plano.getPla_acesso_simultaneo(),
            plano.getPla_nome(),
            plano.getPla_preco()
        };
        sql = sqlInsert;
        execute();

    }

    protected void update(Plano plano) throws Exception {
        args = new Object[]{plano.getPla_id(),
            plano.getPla_acesso_simultaneo(),
            plano.getPla_nome(),
            plano.getPla_preco()
        };
        sql = sqlUpdate;
        execute();
    }
}
