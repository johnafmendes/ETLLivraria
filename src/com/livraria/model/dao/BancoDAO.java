package com.livraria.model.dao;

import com.livraria.constantes.Constantes;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Métodos que interagem com o banco 'LivrariaDM'
 */
public class BancoDAO extends DAO {

    /**
     * Cria banco de dados caso não exista
     *
     * @param banco String
     * @throws DAOException
     */
    public boolean criarBanco(String banco) throws DAOException {
        try {
            String sql = "CREATE DATABASE IF NOT EXISTS "+banco
                    + " DEFAULT CHARACTER SET utf8";
            int result = executeUpdateOrigem(sql, null, true, false);
            return result > 0 ? true : false;
        } finally {
            cleanUpOrigem();
        }
    }

    /**
     * Cria tabela dimensao usuarios_bib
     *
     * @param tabela String
     * @throws DAOException
     */
    public boolean criarTabela(String tabela) throws DAOException {
        try {            
            if (executeUpdateDestino(tabela, null, true, false) < 1) {
                return false;
            }
            return true;
        } finally {
            cleanUpDestino();
        }
    }

    public boolean verificarIndex(String index, String tabela) throws DAOException, SQLException {
        try {
            String sql = "SHOW INDEX FROM " + tabela
                    + " WHERE Key_name = ? ";
            Object[] params = {index};
            ResultSet rs = executeQueryDestino(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado         
                return false;
            }
            return true;
        } finally {
            cleanUpDestino();
        }
    }
}
