/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.controller;

import com.livraria.model.dao.DAOException;
import com.livraria.model.dao.TitulosOrigemDAO;
import com.livraria.model.entidade.Titulo;
import java.util.ArrayList;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class TitulosOrigemController {
    
    private TitulosOrigemDAO toDAO = new TitulosOrigemDAO();
    
    public ArrayList<Titulo> selecionaTudo(Integer de, Integer proximos, boolean copiado) throws DAOException{
        return toDAO.selecionaTudo(de, proximos, copiado);
    }

    public Integer totalTitulos(boolean copiado) throws DAOException {
        return toDAO.TotalTitulos(copiado);
    }
}
