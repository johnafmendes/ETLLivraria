package com.livraria.model.dao;

import com.livraria.constantes.Constantes;
import com.livraria.diversos.Diversos;
import com.livraria.model.entidade.Categoria;
import com.livraria.model.entidade.Contador;
import com.livraria.model.entidade.Curso;
import com.livraria.model.entidade.Grau;
import com.livraria.model.entidade.Historico;
import com.livraria.model.entidade.Serie;
import com.livraria.model.entidade.Turno;
import com.livraria.model.entidade.Usuario_Bib;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jrockit.jfr.tools.ConCatRepository;

/**
 * Métodos que interagem com a tabela 'Usuarios'
 */
public class UsuariosDestinoDAO extends DAO {

    private UsuariosOrigemDAO uoDAO = new UsuariosOrigemDAO();

    public Integer inserirNovosRegistros(ArrayList<Usuario_Bib> ub, Contador contador) throws DAOException, SQLException {
        int inseridos = contador.getContador();
        System.out.println("Tamanho: " + ub.size());
        for (int i = 0; i < ub.size(); i++) {        
            if (insereUsuario(ub.get(i))) {
                uoDAO.marcarCopiado(ub.get(i).getCd_usuario());
                inseridos++;
            }
            contador.setContador(inseridos);
        }
        return inseridos;
    }

    private boolean existeUsuario(String idUsuario) throws DAOException, SQLException {
        try {
            String sql = "SELECT idusuario "
                    + "FROM " + Constantes.NOME_TABELA_USUARIOS
                    + " WHERE idusuario = ? ";
            Object[] params = {idUsuario};
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

    private boolean insereUsuario(Usuario_Bib ub) throws DAOException {
        try {
            String sql = "INSERT INTO " + Constantes.NOME_TABELA_USUARIOS
                    + " (idusuario, categoria, nome, sexo, serie, grau, curso, "
                    + "turno, datacadastro, md5) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            Object[] params = {ub.getCd_usuario(),
                ub.getCategoria().getDs_categoria() == null ? "(Sem Categoria)" : ub.getCategoria().getDs_categoria(),
                ub.getNm_usuario(), ub.getDs_sexo() == null || ub.getDs_sexo().isEmpty() ? "M" : ub.getDs_sexo(),
                ub.getSerie().getDs_serie() == null ? "(Sem Série)" : ub.getSerie().getDs_serie(),
                ub.getGrau().getDs_grau() == null ? "(Sem Grau)" : ub.getGrau().getDs_grau(),
                ub.getCurso().getDs_curso() == null ? "(Sem Curso)" : ub.getCurso().getDs_curso(),
                ub.getTurno().getDs_turno() == null ? "(Sem Turno)" : ub.getTurno().getDs_turno(),
                ub.getDatacadastro() == null || ub.getDatacadastro().equals("") ? "2000-01-01" : ub.getDatacadastro(),
                Diversos.MD5(ub.getCd_usuario()
                + ub.getCategoria().getDs_categoria() + ub.getNm_usuario()
                + ub.getDs_sexo() + ub.getSerie().getDs_serie()
                + ub.getGrau().getDs_grau() + ub.getCurso().getDs_curso()
                + ub.getTurno().getDs_turno() + ub.getDatacadastro())};
            if (executeUpdateDestino(sql, params, true, false) > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            cleanUpDestino();
        }
    }

    public Integer atualizarRegistrosExistentesUsuarios(ArrayList<Usuario_Bib> ub, Contador contador) throws DAOException {
        int alterados = contador.getContador();
        for (int i = 0; i < ub.size(); i++) {
            if (!existeUsuario(ub.get(i).getCd_usuario(), Diversos.MD5(ub.get(i).getCd_usuario()
                    + ub.get(i).getCategoria().getDs_categoria() + ub.get(i).getNm_usuario()
                    + ub.get(i).getDs_sexo() + ub.get(i).getSerie().getDs_serie()
                    + ub.get(i).getGrau().getDs_grau() + ub.get(i).getCurso().getDs_curso()
                    + ub.get(i).getTurno().getDs_turno() + ub.get(i).getDatacadastro()))) {

                atualizaUsuario(ub.get(i), Diversos.MD5(ub.get(i).getCd_usuario()
                        + ub.get(i).getCategoria().getDs_categoria() + ub.get(i).getNm_usuario()
                        + ub.get(i).getDs_sexo() + ub.get(i).getSerie().getDs_serie()
                        + ub.get(i).getGrau().getDs_grau() + ub.get(i).getCurso().getDs_curso()
                        + ub.get(i).getTurno().getDs_turno() + ub.get(i).getDatacadastro()));
                alterados++;
            }
            contador.setContador(alterados);
        }
        return alterados;
    }

    private boolean existeUsuario(String idUsuario, String MD5) throws DAOException {
        try {
            String sql = "SELECT idusuario "
                    + "FROM " + Constantes.NOME_TABELA_USUARIOS
                    + " WHERE idusuario = ? AND md5 = ?";
            Object[] params = {idUsuario, MD5};
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

    private void atualizaUsuario(Usuario_Bib ub, String MD5) throws DAOException {
        try {
            String sql = "UPDATE " + Constantes.NOME_TABELA_USUARIOS
                    + " SET nome = ?, categoria = ?, sexo = ?, serie = ?, "
                    + "grau = ?, curso = ?, turno = ?, datacadastro = ?, "
                    + "md5 = ? "
                    + "WHERE idusuario = ?";
            Object[] params = {ub.getNm_usuario(), ub.getCategoria().getDs_categoria(),
                ub.getDs_sexo(), ub.getSerie().getDs_serie(),
                ub.getGrau().getDs_grau(), ub.getCurso().getDs_curso(),
                ub.getTurno().getDs_turno(), ub.getDatacadastro(), MD5,
                ub.getCd_usuario()};

            executeUpdateDestino(sql, params, true, false);
        } finally {
            cleanUpDestino();
        }
    }

    public void inserirNovosUsuariosAnonimos(ArrayList<Historico> h) throws DAOException, SQLException {
        for (int i = 0; i < h.size(); i++) {
            if (!existeUsuario(h.get(i).getUsuario().getCd_usuario())) {
                Usuario_Bib u = new Usuario_Bib();
                u.setCategoria(new Categoria());
                u.setCurso(new Curso());
                u.setGrau(new Grau());
                u.setSerie(new Serie());
                u.setTurno(new Turno());

                u.setCd_usuario(h.get(i).getUsuario().getCd_usuario());
                u.getCategoria().setDs_categoria("(Sem Categoria)");
                u.setNm_usuario("Anonimo " + i * 10);
                u.setDs_sexo("M");
                u.getCurso().setDs_curso("(Sem Curso)");
                u.getGrau().setDs_grau("(Sem Grau)");
                u.getSerie().setDs_serie("(Sem Série)");
                u.getTurno().setDs_turno("(Sem Turno)");
                u.setDatacadastro(h.get(i).getDt_emprestimo());
                insereUsuario(u);
            }
        }
    }
}
