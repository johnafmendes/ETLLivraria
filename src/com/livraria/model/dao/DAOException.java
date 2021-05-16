package com.livraria.model.dao;

import javax.swing.JOptionPane;

/**
 * Exceção de acesso a dados
 */
public class DAOException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -4383672007024226287L;

    /**
     * @see Exception#Exception()
     */
    public DAOException() {
    }

    /**
     * @see Exception#Exception(String, Throwable)
     * @param message
     * @param cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
        JOptionPane.showMessageDialog(null, message +"\n"+cause);
    }

    /**
     * @see Exception#Exception(String)
     * @param message
     */
    public DAOException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * @see Exception#Exception(Throwable)
     * @param cause
     */
    public DAOException(Throwable cause) {
        super(cause);
        JOptionPane.showMessageDialog(null, cause);
    }
}
