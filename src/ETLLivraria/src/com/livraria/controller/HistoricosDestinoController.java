/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.controller;

import com.livraria.model.dao.DAOException;
import com.livraria.model.dao.HistoricosDestinoDAO;
import com.livraria.model.dao.HistoricosOrigemDAO;
import com.livraria.model.dao.UsuariosDestinoDAO;
import com.livraria.model.entidade.Contador;
import com.livraria.model.entidade.Historico;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class HistoricosDestinoController {

    private HistoricosDestinoDAO hdDAO = new HistoricosDestinoDAO();
    private UsuariosDestinoDAO udDAO = new UsuariosDestinoDAO();
    private HistoricosOrigemDAO hoDAO = new HistoricosOrigemDAO();
    
    public Integer inserirNovosRegistros(ArrayList<Historico> h, Contador contador) throws DAOException, SQLException{        
        return hdDAO.inserirNovosRegistros(h, contador);
    }

    public Integer atualizarRegistrosExistentesHistoricos(ArrayList<Historico> h, Contador contador) throws DAOException {
        return hdDAO.atualizarRegistrosExistentesHistoricos(h, contador);
    }
}
