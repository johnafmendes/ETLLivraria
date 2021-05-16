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
 * @since Mar 22, 2014
 * @version 1.0
 */
public class Historico {

    private String nr_historico;
    private Usuario_Bib usuario;
    private Titulo titulo;
    private Date dt_emprestimo;
    private Date dt_prev_devolucao;
    private Date dt_devolucao;
    private Double vl_pago;
    private Double vl_multa;
    private String fl_pagto;
    private ArrayList<Historico> listaHistorico;

    /**
     * @return the nr_historico
     */
    public String getNr_historico() {
        return nr_historico;
    }

    /**
     * @param nr_historico the nr_historico to set
     */
    public void setNr_historico(String nr_historico) {
        this.nr_historico = nr_historico;
    }

    /**
     * @return the usuario
     */
    public Usuario_Bib getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario_Bib usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the exemplar
     */
    public Titulo getTitulo() {
        return titulo;
    }

    /**
     * @param exemplar the exemplar to set
     */
    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the dt_emprestimo
     */
    public Date getDt_emprestimo() {
        return dt_emprestimo;
    }

    /**
     * @param dt_emprestimo the dt_emprestimo to set
     */
    public void setDt_emprestimo(Date dt_emprestimo) {
        this.dt_emprestimo = dt_emprestimo;
    }

    /**
     * @return the dt_prev_devolucao
     */
    public Date getDt_prev_devolucao() {
        return dt_prev_devolucao;
    }

    /**
     * @param dt_prev_devolucao the dt_prev_devolucao to set
     */
    public void setDt_prev_devolucao(Date dt_prev_devolucao) {
        this.dt_prev_devolucao = dt_prev_devolucao;
    }

    /**
     * @return the dt_devolucao
     */
    public Date getDt_devolucao() {
        return dt_devolucao;
    }

    /**
     * @param dt_devolucao the dt_devolucao to set
     */
    public void setDt_devolucao(Date dt_devolucao) {
        this.dt_devolucao = dt_devolucao;
    }

    /**
     * @return the vl_pago
     */
    public Double getVl_pago() {
        return vl_pago;
    }

    /**
     * @param vl_pago the vl_pago to set
     */
    public void setVl_pago(Double vl_pago) {
        this.vl_pago = vl_pago;
    }

    /**
     * @return the vl_multa
     */
    public Double getVl_multa() {
        return vl_multa;
    }

    /**
     * @param vl_multa the vl_multa to set
     */
    public void setVl_multa(Double vl_multa) {
        this.vl_multa = vl_multa;
    }

    /**
     * @return the fl_pagto
     */
    public String getFl_pagto() {
        return fl_pagto;
    }

    /**
     * @param fl_pagto the fl_pagto to set
     */
    public void setFl_pagto(String fl_pagto) {
        this.fl_pagto = fl_pagto;
    }

    /**
     * @return the listaHistorico
     */
    public ArrayList<Historico> getListaHistorico() {
        return listaHistorico;
    }

    /**
     * @param listaHistorico the listaHistorico to set
     */
    public void setListaHistorico(ArrayList<Historico> listaHistorico) {
        this.listaHistorico = listaHistorico;
    }

        
}
