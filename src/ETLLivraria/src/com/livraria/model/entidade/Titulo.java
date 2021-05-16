/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.model.entidade;

import java.util.ArrayList;

/**
 *
 * @author John
 * @since Mar 22, 2014
 * @version 1.0
 */
public class Titulo {

    private String cd_titulo;
    private String ds_titulo;
    private ArrayList<Titulo> listaTitulos;

    /**
     * @return the cd_titulo
     */
    public String getCd_titulo() {
        return cd_titulo;
    }

    /**
     * @param cd_titulo the cd_titulo to set
     */
    public void setCd_titulo(String cd_titulo) {
        this.cd_titulo = cd_titulo;
    }

    /**
     * @return the ds_titulo
     */
    public String getDs_titulo() {
        return ds_titulo;
    }

    /**
     * @param ds_titulo the ds_titulo to set
     */
    public void setDs_titulo(String ds_titulo) {
        this.ds_titulo = ds_titulo;
    }

    /**
     * @return the listaTitulos
     */
    public ArrayList<Titulo> getListaTitulos() {
        return listaTitulos;
    }

    /**
     * @param listaTitulos the listaTitulos to set
     */
    public void setListaTitulos(ArrayList<Titulo> listaTitulos) {
        this.listaTitulos = listaTitulos;
    }
    
    
}
