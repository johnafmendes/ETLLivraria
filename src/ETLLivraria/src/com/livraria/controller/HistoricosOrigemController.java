/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.controller;

import com.livraria.model.dao.DAOException;
import com.livraria.model.dao.HistoricosOrigemDAO;
import com.livraria.model.entidade.Historico;
import java.util.ArrayList;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class HistoricosOrigemController {
    
    private HistoricosOrigemDAO hoDAO = new HistoricosOrigemDAO();
    
    public ArrayList<Historico> selecionaTudo(Integer de, Integer proximos, boolean copiado) throws DAOException{
        return hoDAO.selecionaTudo(de, proximos, copiado);
    }
 
//    public int TotalRegistrosComUsuariosRegistrados() throws DAOException {
//        return hoDAO.getTotalRegistrosComUsuariosRegistrados();
//    }

//    public ArrayList<Historico> selecionaRegistrosAnonimos(int de, int ate) throws DAOException {
//        return hoDAO.selecionaRegistrosAnonimos(de, ate);
//    }

    public Integer totalRegistros(boolean copiado) throws DAOException {
        return hoDAO.getTotalRegistros(copiado);
    }

    public ArrayList<Historico> selecionaUsuariosAnonimos(int de, int proximos) throws DAOException {
        return hoDAO.selecionaUsuariosAnonimos(de, proximos);
    }

    public Integer totalRegistrosUsuariosAnonimos() throws DAOException {
        return hoDAO.getTotalRegistrosUsuariosAnonimos();
    }

    public Integer totalRegistrosTitulosAnonimos() throws DAOException {
        return hoDAO.getTotalRegistrosTitulosAnonimos();
    }

    public ArrayList<Historico> selecionaTitulosAnonimos(int de, int proximos) throws DAOException {
        return hoDAO.selecionaTitulosAnonimos(de, proximos);
    }

}
