/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.controller;

import com.livraria.model.dao.DAOException;
import com.livraria.model.dao.UsuariosDestinoDAO;
import com.livraria.model.entidade.Contador;
import com.livraria.model.entidade.Historico;
import com.livraria.model.entidade.Usuario_Bib;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class UsuariosDestinoController {

    private UsuariosDestinoDAO udDAO = new UsuariosDestinoDAO();
    
    public Integer inserirNovosRegistros(ArrayList<Usuario_Bib> ub, Contador contador) throws DAOException, SQLException{
        return udDAO.inserirNovosRegistros(ub, contador);
    }

    public Integer atualizarRegistrosExistentesUsuarios(ArrayList<Usuario_Bib> ub, Contador contador) throws DAOException {
        return udDAO.atualizarRegistrosExistentesUsuarios(ub, contador);
    }

    public void inserirNovosUsuariosAnonimos(ArrayList<Historico> h) throws DAOException, SQLException {
        udDAO.inserirNovosUsuariosAnonimos(h);
    }
}
