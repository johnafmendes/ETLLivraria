/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.controller;

import com.livraria.model.dao.DAOException;
import com.livraria.model.dao.UsuariosOrigemDAO;
import com.livraria.model.entidade.Usuario_Bib;
import java.util.ArrayList;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class UsuariosOrigemController {
    
    private UsuariosOrigemDAO uoDAO = new UsuariosOrigemDAO();
    
    public ArrayList<Usuario_Bib> selecionaTudo(Integer de, Integer proximos, boolean copiado) throws DAOException{
        return uoDAO.selecionaTudo(de, proximos, copiado);
    }

    public Integer totalUsuarios(boolean copiado) throws DAOException {
        return uoDAO.totalUsuarios(copiado);
    }

}
