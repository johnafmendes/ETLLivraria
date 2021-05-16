package com.livraria.model.dao;

import com.livraria.constantes.Constantes;
import com.livraria.diversos.Diversos;
import com.livraria.model.entidade.Categoria;
import com.livraria.model.entidade.Contador;
import com.livraria.model.entidade.Curso;
import com.livraria.model.entidade.Grau;
import com.livraria.model.entidade.Historico;
import com.livraria.model.entidade.Serie;
import com.livraria.model.entidade.Titulo;
import com.livraria.model.entidade.Turno;
import com.livraria.model.entidade.Usuario_Bib;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Métodos que interagem com a tabela 'Usuarios'
 */
public class TitulosDestinoDAO extends DAO {

    private TitulosOrigemDAO toDAO = new TitulosOrigemDAO();

    public Integer inserirNovosRegistros(ArrayList<Titulo> t, Contador contador) throws DAOException, SQLException {
        int inseridos = contador.getContador();
        for (int i = 0; i < t.size(); i++) {
            if (insereTitulo(t.get(i))) {
                toDAO.marcarCopiado(t.get(i).getCd_titulo());
                inseridos++;
            }
            contador.setContador(inseridos);
        }
        return inseridos;
    }

    private boolean existeTitulo(String idTitulo) throws DAOException, SQLException {
        try {
            String sql = "SELECT idtitulo "
                    + "FROM " + Constantes.NOME_TABELA_TITULOS
                    + " WHERE idtitulo = ? ";
            Object[] params = {idTitulo};
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

    private boolean insereTitulo(Titulo t) throws DAOException {
        try {
            String sql = "INSERT INTO " + Constantes.NOME_TABELA_TITULOS
                    + " (idtitulo, titulo, md5) "
                    + "VALUES (?, ?, ?)";

            Object[] params = {t.getCd_titulo(), t.getDs_titulo(),
                Diversos.MD5(t.getCd_titulo() + t.getDs_titulo())};
            if (executeUpdateDestino(sql, params, true, false) > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            cleanUpDestino();
        }
    }

    public Integer atualizarRegistrosExistentesTitulos(ArrayList<Titulo> t, Contador contador) throws DAOException {
        int alterados = contador.getContador();
        for (int i = 0; i < t.size(); i++) {
            if (!existeTitulo(t.get(i).getCd_titulo(), Diversos.MD5(t.get(i).getCd_titulo()
                    + t.get(i).getDs_titulo()))) {

                atualizaTitulo(t.get(i), Diversos.MD5(t.get(i).getCd_titulo()
                        + t.get(i).getDs_titulo()));
                alterados++;
            }
            contador.setContador(alterados);
        }
        return alterados;
    }

    private boolean existeTitulo(String idTitulo, String MD5) throws DAOException {
        try {
            String sql = "SELECT idtitulo "
                    + "FROM " + Constantes.NOME_TABELA_TITULOS
                    + " WHERE idtitulo = ? AND md5 = ?";
            Object[] params = {idTitulo, MD5};
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

    private void atualizaTitulo(Titulo t, String MD5) throws DAOException {
        try {
            String sql = "UPDATE " + Constantes.NOME_TABELA_TITULOS
                    + " SET titulo = ?, md5 = ? "
                    + "WHERE idtitulo = ?";
            Object[] params = {t.getDs_titulo(), MD5, t.getCd_titulo()};

            executeUpdateDestino(sql, params, true, false);
        } finally {
            cleanUpDestino();
        }
    }

    public void inserirNovosTitulosAnonimos(ArrayList<Historico> h) throws DAOException, SQLException {
        for (int i = 0; i < h.size(); i++) {
//            if (h.get(i).getTitulo().getCd_titulo() != null) {
            if (!existeTitulo(h.get(i).getTitulo().getCd_titulo())) {
                Titulo t = new Titulo();

                t.setCd_titulo(h.get(i).getTitulo().getCd_titulo());
                t.setDs_titulo("Titulo Anonimo " + i * 10);
                insereTitulo(t);
            }
//            }
        }
    }
}
