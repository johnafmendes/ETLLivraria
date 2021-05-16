package com.livraria.model.dao;

import com.livraria.model.entidade.Categoria;
import com.livraria.model.entidade.Curso;
import com.livraria.model.entidade.Grau;
import com.livraria.model.entidade.Serie;
import com.livraria.model.entidade.Turno;
import com.livraria.model.entidade.Usuario_Bib;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Métodos que interagem com a tabela 'Usuarios'
 */
public class UsuariosOrigemDAO extends DAO {

    /**
     * 
     * @return
     * @throws DAOException 
     */
    public ArrayList<Usuario_Bib> selecionaTudo(Integer de, Integer proximos, boolean copiado) throws DAOException{
        try {
            String sql = "SELECT u.CD_USUARIO, u.CD_CATEGORIA, c.DS_CATEGORIA, "
                    + "u.NM_USUARIO, u.DS_SEXO, s.CD_SERIE, s.DS_SERIE, "
                    + "g.CD_GRAU, g.DS_GRAU, cu.CD_CURSO, t.CD_TURNO, "
                    + "cu.DS_CURSO, t.DS_TURNO, u.DT_CADASTRO "
                    + "FROM usuarios_bib u "
                    + "LEFT JOIN categorias c ON c.CD_CATEGORIA = u.CD_CATEGORIA "
                    + "LEFT JOIN serie s ON s.CD_SERIE = u.CD_SERIE "
                    + "LEFT JOIN grau g ON g.CD_GRAU = u.CD_GRAU "
                    + "LEFT JOIN curso cu ON cu.CD_CURSO = u.CD_CURSO "
                    + "LEFT JOIN turno t ON t.CD_TURNO = u.CD_TURNO "
                    + "WHERE copiado = ? "
                    + "GROUP BY u.CD_USUARIO "
                    + "LIMIT ?, ?";
            Object[] params = {copiado, de, proximos};
            ResultSet rs = executeQueryOrigem(sql, params);

            if (!rs.next()) {
                // Registro não foi encontrado
                return null;
            }

            // Cria um objeto Serie e popula com os dados vindos da tabela
            Usuario_Bib ub = new Usuario_Bib();
            ub.setListaUsuario_Bib(new ArrayList<Usuario_Bib>());
            populateObject(ub, rs);
            return ub.getListaUsuario_Bib();

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
    private void populateObject(Usuario_Bib usuario_Bib, ResultSet rs) throws DAOException {
        try {
            do{
                Usuario_Bib ub = new Usuario_Bib();
                ub.setCategoria(new Categoria());
                ub.setSerie(new Serie());
                ub.setCurso(new Curso());
                ub.setGrau(new Grau());
                ub.setTurno(new Turno());
                
                ub.setCd_usuario(rs.getString("CD_USUARIO"));
                ub.getCategoria().setCd_categoria(rs.getString("CD_CATEGORIA"));
                ub.getCategoria().setDs_categoria(rs.getString("DS_CATEGORIA"));
                ub.setNm_usuario(rs.getString("NM_USUARIO"));
                ub.setDs_sexo(rs.getString("DS_SEXO"));
                ub.getSerie().setCd_serie(rs.getString("CD_SERIE"));
                ub.getSerie().setDs_serie(rs.getString("DS_SERIE"));
                ub.getCurso().setCd_curso(rs.getString("CD_CURSO"));
                ub.getCurso().setDs_curso(rs.getString("DS_CURSO"));
                ub.getGrau().setCd_grau(rs.getString("CD_GRAU"));
                ub.getGrau().setDs_grau(rs.getString("DS_GRAU"));
                ub.getTurno().setCd_turno(rs.getString("CD_TURNO"));
                ub.getTurno().setDs_turno(rs.getString("DS_TURNO"));
                ub.setDatacadastro(rs.getDate("DT_CADASTRO"));
                
                usuario_Bib.getListaUsuario_Bib().add(ub);
            } while(rs.next());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Integer totalUsuarios(boolean copiado) throws DAOException {
        try {
            String sql = "SELECT COUNT(*) "
                    + "FROM usuarios_bib "
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

    void marcarCopiado(String idUsuario) throws DAOException {
        try {
            String sql = "UPDATE usuarios_bib SET copiado = ? "
                    + "WHERE CD_USUARIO = ?";
            Object[] params = {true, idUsuario};

            executeUpdateOrigem(sql, params, true, false);
        } finally {
            cleanUpOrigem();
        }
    }

}
