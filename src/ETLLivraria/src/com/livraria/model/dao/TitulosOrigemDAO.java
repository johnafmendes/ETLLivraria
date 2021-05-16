package com.livraria.model.dao;

import com.livraria.model.entidade.Titulo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Métodos que interagem com a tabela 'Usuarios'
 */
public class TitulosOrigemDAO extends DAO {

    /**
     * 
     * @return
     * @throws DAOException 
     */
    public ArrayList<Titulo> selecionaTudo(Integer de, Integer proximos, boolean copiado) throws DAOException{
        try {
            String sql = "SELECT CD_MATERIAL, DS_TITULO "
                    + "FROM titulos "
                    + "WHERE copiado = ? "
                    + "LIMIT ?, ?";
            Object[] params = {copiado, de, proximos};
            ResultSet rs = executeQueryOrigem(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado
                return null;
            }

            // Cria um objeto Serie e popula com os dados vindos da tabela
            Titulo t = new Titulo();
            t.setListaTitulos(new ArrayList<Titulo>());
            populateObject(t, rs);
            return t.getListaTitulos();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpOrigem();
        }
    }
    
    /**
     * Popula um objeto Cliente com base nos dados vindos da tabela
     *
     * @param cliente Objeto a ser populado
     * @param rs ResultSet com os dados que serão extraídos
     * @throws DAOException
     */
    private void populateObject(Titulo titulo, ResultSet rs) throws DAOException {
        try {
            do{
                Titulo t = new Titulo();
                
                t.setCd_titulo(rs.getString("CD_MATERIAL"));
                t.setDs_titulo(rs.getString("DS_TITULO"));
                
                titulo.getListaTitulos().add(t);
            } while(rs.next());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Integer TotalTitulos(boolean copiado) throws DAOException {
        try {
            String sql = "SELECT COUNT(*) "
                    + "FROM titulos "
                    + "WHERE copiado = ?";
            Object[] params = {copiado};
            ResultSet rs = executeQueryOrigem(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado
                return 0;
            }

            return rs.getInt(1);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpOrigem();
        }
    }

    void marcarCopiado(String idTitulo) throws DAOException {
        try {
            String sql = "UPDATE titulos SET copiado = ? "
                    + "WHERE CD_MATERIAL = ?";
            Object[] params = {true, idTitulo};

            executeUpdateOrigem(sql, params, true, false);
        } finally {
            cleanUpOrigem();
        }
    }

}
