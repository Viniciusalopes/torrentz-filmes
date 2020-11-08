/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD

=======
>>>>>>> main
package br.com.torrentz.bll;

import br.com.torrentz.dal.DalCategoria;
import br.com.torrentz.model.Categoria;

/**
 *
<<<<<<< HEAD
 * @author marcos
 */
public class BllCategoria extends DalCategoria{
    
    public BllCategoria() throws Exception{super();}
    /**
     * 
     * @param categoria
     * @throws Exception 
     */
    public void add(Categoria categoria) throws Exception {
        super.add(categoria); //To change body of generated methods, choose Tools | Templates.
        
    }    
    
    /**
     * 
     * @param categoria
     * @throws Exception 
     */
    public void update(Categoria categoria) throws Exception {
        super.update(categoria); //To change body of generated methods, choose Tools | Templates.
=======
 * @author calebaum
 */
public class BllCategoria extends DalCategoria {

    public BllCategoria() throws Exception {
        super();
    }

    public void add(Categoria categoria) throws Exception {
        validate(categoria);
        super.add(categoria);

    }

    public void delete(int cat_id) throws Exception {
        super.delete(cat_id);
    }

    public void update(Categoria categoria) throws Exception {
        validate(categoria);
        super.update(categoria);
    }

    public void validate(Categoria categoria) throws Exception {
        if (categoria.getCat_nome().trim().length() < 2) {
            throw new Exception("Nome da categoria muito curto");
        }
        if (categoria.getCat_nome().trim().length() > super.getMaxLength("cat_nome")) {
            throw new Exception("Nome da categoria muito longo ");
        }

        if (super.alreadyExists(categoria.getCat_nome(), categoria.getCat_id())) {
            throw new Exception("Já existe uma categoria com o nome " + categoria.getCat_nome());
        }
>>>>>>> main
    }
}
