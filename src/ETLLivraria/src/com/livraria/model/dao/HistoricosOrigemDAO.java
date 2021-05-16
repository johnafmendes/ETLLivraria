package com.livraria.model.dao;

import com.livraria.constantes.Constantes;
import com.livraria.model.entidade.Historico;
import com.livraria.model.entidade.Titulo;
import com.livraria.model.entidade.Usuario_Bib;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Métodos que interagem com a tabela 'Usuarios'
 */
public class HistoricosOrigemDAO extends DAO {

    /**
     *
     * @return @throws DAOException
     */
    public ArrayList<Historico> selecionaTudo(Integer de, Integer proximos, boolean copiado) throws DAOException {
        try {
            String sql = "SELECT h.NR_HISTORICO, h.CD_USUARIO, h.NR_EXEMPLAR, "
                    + "h.DT_EMPRESTIMO, h.DT_PREV_DEVOLUCAO, h.DT_DEVOLUCAO, "
                    + "h.VL_PAGO, h.VL_MULTA, h.FL_PAGTO "
                    + "FROM historico h "
                    + "WHERE h.NR_EXEMPLAR IS NOT NULL "
                    + "AND h.CD_USUARIO IS NOT NULL "
                    + "AND h.copiado = ? "
                    + "LIMIT ?, ?";
            Object[] params = {copiado, de, proximos};
            ResultSet rs = executeQueryOrigem(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado
                return null;
            }

            // Cria um objeto Serie e popula com os dados vindos da tabela
            Historico h = new Historico();
            h.setListaHistorico(new ArrayList<Historico>());
            populateObject(h, rs);
            return h.getListaHistorico();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpOrigem();
        }
    }

    /**
     * Seleciona historico com usuarios anonimos
     *
     * @return
     * @throws DAOException
     */
//    public ArrayList<Historico> selecionaRegistrosAnonimos(Integer de, Integer ate) throws DAOException{
//        try {
//            String sql = "SELECT h.NR_HISTORICO, h.CD_USUARIO, h.NR_EXEMPLAR, "
//                    + "h.DT_EMPRESTIMO, h.DT_PREV_DEVOLUCAO, h.DT_DEVOLUCAO, "
//                    + "h.VL_PAGO, h.VL_MULTA, h.FL_PAGTO "
//                    + "FROM historico h "
//                    + "WHERE h.CD_USUARIO NOT IN (SELECT CD_USUARIO FROM usuarios_bib) "
//                    + "AND h.NR_EXEMPLAR IS NOT NULL AND h.CD_USUARIO IS NOT NULL "
//                    + "LIMIT ?, ?";
//            Object[] params = {de, ate};
//            ResultSet rs = executeQueryOrigem(sql, params);
//
//            if (!rs.next()) {
//                // Registro não foi encontrado
//                return null;
//            }
//
//            // Cria um objeto Serie e popula com os dados vindos da tabela
//            Historico h = new Historico();
//            h.setListaHistorico(new ArrayList<Historico>());
//            populateObject(h, rs);
//            return h.getListaHistorico();
//
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            cleanUpOrigem();
//        }
//    }
    /**
     * Popula um objeto Cliente com base nos dados vindos da tabela
     *
     * @param cliente Objeto a ser populado
     * @param rs ResultSet com os dados que serão extraídos
     * @throws DAOException
     */
    private void populateObject(Historico historico, ResultSet rs) throws DAOException {
        try {
            do {
                Historico h = new Historico();
                h.setUsuario(new Usuario_Bib());
                h.setTitulo(new Titulo());

                h.setNr_historico(rs.getString("NR_HISTORICO"));
                h.getUsuario().setCd_usuario(rs.getString("CD_USUARIO"));
                h.getTitulo().setCd_titulo(rs.getString("NR_EXEMPLAR"));
                h.setDt_emprestimo(rs.getDate("DT_EMPRESTIMO"));
                h.setDt_prev_devolucao(rs.getDate("DT_PREV_DEVOLUCAO"));
                h.setDt_devolucao(rs.getDate("DT_DEVOLUCAO"));
                h.setVl_pago(rs.getDouble("VL_PAGO"));
                h.setVl_multa(rs.getDouble("VL_MULTA"));
                h.setFl_pagto(rs.getString("FL_PAGTO"));

                historico.getListaHistorico().add(h);
            } while (rs.next());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public int getTotalRegistros(boolean copiado) throws DAOException {
        try {
            String sql = "SELECT COUNT(*) "
                    + "FROM historico "
                    + "WHERE NR_EXEMPLAR IS NOT NULL "
                    + "AND CD_USUARIO IS NOT NULL "
                    + "AND copiado = ?";
            Object[] params = {copiado};
            ResultSet rs = executeQueryOrigem(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado
                return 0;
            }

            // Cria um objeto Serie e popula com os dados vindos da tabela
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpOrigem();
        }
    }

//    public int getTotalRegistrosComUsuariosRegistrados() throws DAOException {
//        try {
//            String sql = "SELECT COUNT(*) "
//                    + "FROM historico h "
//                    + "INNER JOIN usuarios_bib u ON u.CD_USUARIO = h.CD_USUARIO "
//                    + "WHERE h.NR_EXEMPLAR IS NOT NULL";
//            ResultSet rs = executeQueryOrigem(sql, null);
//
//            if (!rs.next()) {
//                // Registro não foi encontrado
//                return 0;
//            }
//
//            // Cria um objeto Serie e popula com os dados vindos da tabela
//            return rs.getInt(1);
//
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            cleanUpOrigem();
//        }
//    }
    public ArrayList<Historico> selecionaUsuariosAnonimos(int de, int proximos) throws DAOException {
        try {
            String sql = "SELECT DISTINCT h.CD_USUARIO, (SELECT DT_EMPRESTIMO "
                    + "FROM historico "
                    + "WHERE CD_USUARIO=h.CD_USUARIO LIMIT 1) as dataCadastro "
                    + "FROM historico h "
                    + "WHERE h.CD_USUARIO NOT IN (SELECT CD_USUARIO FROM usuarios_bib) "
                    + "AND h.CD_USUARIO IS NOT NULL "
                    + "LIMIT ?, ?";
            Object[] params = {de, proximos};
            ResultSet rs = executeQueryOrigem(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado
                return null;
            }

            // Cria um objeto Serie e popula com os dados vindos da tabela
            Historico h = new Historico();
            h.setListaHistorico(new ArrayList<Historico>());
            populateObjectUsuarios(h, rs);
            return h.getListaHistorico();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpOrigem();
        }
    }

    private void populateObjectUsuarios(Historico historico, ResultSet rs) throws DAOException {
        try {
            do {
                Historico h = new Historico();
                h.setUsuario(new Usuario_Bib());

                h.getUsuario().setCd_usuario(rs.getString("CD_USUARIO"));
                h.setDt_emprestimo(rs.getDate("dataCadastro") == null ? Date.valueOf("2000-01-01") : rs.getDate("dataCadastro"));

                historico.getListaHistorico().add(h);
            } while (rs.next());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Integer getTotalRegistrosUsuariosAnonimos() throws DAOException {
        try {
            String sql = "SELECT COUNT(DISTINCT h.CD_USUARIO) "
                    + "FROM historico h "
                    + "WHERE h.CD_USUARIO NOT IN (SELECT CD_USUARIO FROM usuarios_bib) "
                    + "AND h.CD_USUARIO IS NOT NULL";
            ResultSet rs = executeQueryOrigem(sql, null);

            if (!rs.next()) {
                // Registro não foi encontrado
                return 0;
            }

            // Cria um objeto Serie e popula com os dados vindos da tabela
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpOrigem();
        }
    }

    public Integer getTotalRegistrosTitulosAnonimos() throws DAOException {
        try {
            String sql = "SELECT COUNT(DISTINCT h.NR_EXEMPLAR) "
                    + "FROM historico h "
                    + "WHERE h.NR_EXEMPLAR NOT IN (SELECT CD_MATERIAL FROM titulos) "
                    + "AND h.NR_EXEMPLAR IS NOT NULL";
            ResultSet rs = executeQueryOrigem(sql, null);

            if (!rs.next()) {
                // Registro não foi encontrado
                return 0;
            }

            // Cria um objeto Serie e popula com os dados vindos da tabela
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpOrigem();
        }
    }

    public ArrayList<Historico> selecionaTitulosAnonimos(int de, int proximos) throws DAOException {
        try {
            String sql = "SELECT DISTINCT h.NR_EXEMPLAR "
                    + "FROM historico h "
                    + "WHERE h.NR_EXEMPLAR NOT IN (SELECT CD_MATERIAL FROM titulos) "
                    + "AND h.NR_EXEMPLAR IS NOT NULL "
                    + "LIMIT ?, ?";
            Object[] params = {de, proximos};
            ResultSet rs = executeQueryOrigem(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado
                return null;
            }

            // Cria um objeto Serie e popula com os dados vindos da tabela
            Historico h = new Historico();
            h.setListaHistorico(new ArrayList<Historico>());
            populateObjectTitulos(h, rs);
            return h.getListaHistorico();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpOrigem();
        }
    }

    private void populateObjectTitulos(Historico historico, ResultSet rs) throws DAOException {
        try {
            do {
                Historico h = new Historico();
                h.setTitulo(new Titulo());

                h.getTitulo().setCd_titulo(rs.getString("NR_EXEMPLAR"));

                historico.getListaHistorico().add(h);
            } while (rs.next());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    void marcarCopiado(String idHistorico) throws DAOException {
        try {
            String sql = "UPDATE historico SET copiado = ? "
                    + "WHERE NR_HISTORICO = ?";
            Object[] params = {true, idHistorico};

            executeUpdateOrigem(sql, params, true, false);
        } finally {
            cleanUpOrigem();
        }
    }
}
