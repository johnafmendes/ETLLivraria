package com.livraria.model.dao;

import com.livraria.constantes.Constantes;
import com.livraria.diversos.Diversos;
import com.livraria.model.entidade.Contador;
import com.livraria.model.entidade.Historico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Métodos que interagem com a tabela 'Usuarios'
 */
public class HistoricosDestinoDAO extends DAO {
    
    private HistoricosOrigemDAO hoDAO = new HistoricosOrigemDAO();
    
    public Integer inserirNovosRegistros(ArrayList<Historico> h, Contador contador) throws DAOException, SQLException {
        int inseridos = contador.getContador();
        for (int i = 0; i < h.size(); i++) {
//            if (!existeHistorico(h.get(i).getNr_historico())) {
                if(insereHistorico(h.get(i))){
                    hoDAO.marcarCopiado(h.get(i).getNr_historico());
                    inseridos++;
                }
                contador.setContador(inseridos);
//            }
        }
        return contador.getContador();
    }
    
    private boolean existeHistorico(String idHistorico) throws DAOException, SQLException {
        try{
            String sql = "SELECT idhistorico "
                    + "FROM " + Constantes.NOME_TABELA_HISTORICOS
                    + " WHERE idhistorico = ? ";
            Object[] params = {idHistorico};
            ResultSet rs = executeQueryDestino(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado         
                return false;
            }            
            return true;
        }finally{
            cleanUpDestino();
        }
    }

    private boolean insereHistorico(Historico h) throws DAOException {
        try {
            String sql = "INSERT INTO " + Constantes.NOME_TABELA_HISTORICOS
                    + " (idhistorico, idusuario, idtitulo, dataemprestimo, "
                    + "dataprevisaodevolucao, datadevolucao, valorpago, "
                    + "valormulta, pagamento, md5) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            Object[] params = {h.getNr_historico(), h.getUsuario().getCd_usuario(),
                h.getTitulo().getCd_titulo(), h.getDt_emprestimo(), 
                h.getDt_prev_devolucao(), h.getDt_devolucao(), 
                h.getVl_pago(), h.getVl_multa(), h.getFl_pagto(),
                Diversos.MD5(h.getNr_historico() + h.getUsuario().getCd_usuario()
                + h.getTitulo().getCd_titulo() + h.getDt_emprestimo()
                + h.getDt_prev_devolucao() + h.getDt_devolucao() 
                + h.getVl_pago() + h.getVl_multa() + h.getFl_pagto())};
            if (executeUpdateDestino(sql, params, true, false) > 0){
                return true;
            }else{
                return false;
            }
        } finally {
            cleanUpDestino();
        }
    }

    public Integer atualizarRegistrosExistentesHistoricos(ArrayList<Historico> h, Contador contador) throws DAOException {
        int alterados = 0;
        for (int i = 0; i < h.size(); i++) {
            if (!existeHistorico(h.get(i).getNr_historico(), Diversos.MD5(h.get(i).getNr_historico() 
                + h.get(i).getUsuario().getCd_usuario()
                + h.get(i).getTitulo().getCd_titulo() + h.get(i).getDt_emprestimo()
                + h.get(i).getDt_prev_devolucao() + h.get(i).getDt_devolucao() 
                + h.get(i).getVl_pago() + h.get(i).getVl_multa() + h.get(i).getFl_pagto()))) {
        
                atualizaHistorico(h.get(i), Diversos.MD5(h.get(i).getNr_historico() 
                + h.get(i).getUsuario().getCd_usuario()
                + h.get(i).getTitulo().getCd_titulo() + h.get(i).getDt_emprestimo()
                + h.get(i).getDt_prev_devolucao() + h.get(i).getDt_devolucao() 
                + h.get(i).getVl_pago() + h.get(i).getVl_multa() + h.get(i).getFl_pagto()));
                alterados++;
            }
            contador.setContador(alterados);
        }
        return alterados;
    }

    private boolean existeHistorico(String idHistorico, String MD5) throws DAOException {
        try {
            String sql = "SELECT idhistorico "
                    + "FROM " + Constantes.NOME_TABELA_HISTORICOS
                    + " WHERE idhistorico = ? AND md5 = ?";
            Object[] params = {idHistorico, MD5};
            ResultSet rs = executeQueryDestino(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado
                return false;
            }

            return true;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            cleanUpDestino();
        }
    }

    private void atualizaHistorico(Historico h, String MD5) throws DAOException {
        try {
            String sql = "UPDATE "+Constantes.NOME_TABELA_HISTORICOS
                    + " SET idusuario = ?, idtitulo = ?, dataemprestimo = ?, "
                    + "dataprevisaodevolucao = ?, datadevolucao = ?, "
                    + "valorpago = ?, valormulta = ?, pagamento = ?, md5 = ? "
                    + "WHERE idhistorico = ?";
            Object[] params = {h.getUsuario().getCd_usuario(),
                h.getTitulo().getCd_titulo(), h.getDt_emprestimo(), 
                h.getDt_prev_devolucao(), h.getDt_devolucao(), 
                h.getVl_pago(), h.getVl_multa(), h.getFl_pagto(), MD5, 
                h.getNr_historico()};
            
            executeUpdateDestino(sql, params, true, false);
        } finally {
            cleanUpDestino();
        }
    }
}
