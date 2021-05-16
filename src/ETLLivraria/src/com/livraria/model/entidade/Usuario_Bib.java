/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.model.entidade;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class Usuario_Bib {

    private String cd_usuario;
    private Categoria categoria;
    private String nm_usuario;
    private String ds_sexo;
    private Serie serie;
    private Grau grau;
    private Curso curso;
    private Turno turno;
    private Date datacadastro;
    private ArrayList<Usuario_Bib> listaUsuario_Bib;

    /**
     * @return the cd_usuario
     */
    public String getCd_usuario() {
        return cd_usuario;
    }

    /**
     * @param cd_usuario the cd_usuario to set
     */
    public void setCd_usuario(String cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the nm_usuario
     */
    public String getNm_usuario() {
        return nm_usuario;
    }

    /**
     * @param nm_usuario the nm_usuario to set
     */
    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    /**
     * @return the ds_sexo
     */
    public String getDs_sexo() {
        return ds_sexo;
    }

    /**
     * @param ds_sexo the ds_sexo to set
     */
    public void setDs_sexo(String ds_sexo) {
        this.ds_sexo = ds_sexo;
    }

    /**
     * @return the serie
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    /**
     * @return the grau
     */
    public Grau getGrau() {
        return grau;
    }

    /**
     * @param grau the grau to set
     */
    public void setGrau(Grau grau) {
        this.grau = grau;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * @return the turno
     */
    public Turno getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    /**
     * @return the datacadastro
     */
    public Date getDatacadastro() {
        return datacadastro;
    }

    /**
     * @param datacadastro the datacadastro to set
     */
    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * @return the listaUsuario_Bib
     */
    public ArrayList<Usuario_Bib> getListaUsuario_Bib() {
        return listaUsuario_Bib;
    }

    /**
     * @param listaUsuario_Bib the listaUsuario_Bib to set
     */
    public void setListaUsuario_Bib(ArrayList<Usuario_Bib> listaUsuario_Bib) {
        this.listaUsuario_Bib = listaUsuario_Bib;
    }

}
