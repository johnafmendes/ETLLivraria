/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.controller;

import com.livraria.model.dao.BancoDAO;
import com.livraria.model.dao.DAOException;
import java.sql.SQLException;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class BancoController {

    private BancoDAO bancoDAO = new BancoDAO();
    
    public boolean criarBanco(String banco) throws DAOException{
        return bancoDAO.criarBanco(banco);
    }
    
    public boolean criarTabela (String tabela) throws DAOException {
        return bancoDAO.criarTabela(tabela);
    }

    public boolean verificarIndex(String index, String tabela) throws DAOException, SQLException {
        return bancoDAO.verificarIndex(index, tabela);
    }
}
