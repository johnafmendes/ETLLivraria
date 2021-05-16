/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.controller;

import com.livraria.model.dao.DAOException;
import com.livraria.model.dao.TitulosDestinoDAO;
import com.livraria.model.entidade.Contador;
import com.livraria.model.entidade.Historico;
import com.livraria.model.entidade.Titulo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class TitulosDestinoController {

    private TitulosDestinoDAO tdDAO = new TitulosDestinoDAO();
    
    public Integer inserirNovosRegistros(ArrayList<Titulo> t, Contador contador) throws DAOException, SQLException{
        return tdDAO.inserirNovosRegistros(t, contador);
    }

    public Integer atualizarRegistrosExistentesTitulos(ArrayList<Titulo> t, Contador contador) throws DAOException {
        return tdDAO.atualizarRegistrosExistentesTitulos(t, contador);
    }

    public void inserirNovosTitulosAnonimos(ArrayList<Historico> h) throws DAOException, SQLException {
        tdDAO.inserirNovosTitulosAnonimos(h);
    }
}
