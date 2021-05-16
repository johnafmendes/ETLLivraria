/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livraria.constantes;

/**
 *
 * @author John
 * @since Mar 19, 2014
 * @version 1.0
 */
public class Constantes {

    public static final String BANCO = "livrariadm";
    public static final String NOME_TABELA_USUARIOS = "usuarios";
    public static final String NOME_TABELA_TITULOS = "titulos";
    public static final String NOME_TABELA_HISTORICOS = "historicos";
    public static final String NOME_INDEX_USUARIOS = "usuarios_idusuario_idx";
    public static final String NOME_INDEX_HISTORICOS = "historicos_idhistorico_idx";
    public static final String NOME_INDEX_TITULOS = "titulos_idtitulo_idx";
    
    public static final String TABELA_DIMENSAO_USUARIOS = "CREATE TABLE IF NOT "
            + "EXISTS "+NOME_TABELA_USUARIOS+" ("
            + "idusuario VARCHAR(15) NOT NULL, "
            + "categoria VARCHAR(100) NOT NULL, "
            + "nome VARCHAR(100) NOT NULL, "
            + "sexo VARCHAR(1) NOT NULL, "
            + "serie VARCHAR(100) NOT NULL, "
            + "grau VARCHAR(100) NOT NULL, "
            + "curso VARCHAR(100) NOT NULL, "
            + "turno VARCHAR(100) NOT NULL, "
            + "datacadastro DATETIME NOT NULL, "
            + "md5 VARCHAR(32) NOT NULL, "
            + "PRIMARY KEY (idusuario)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
    
    public static final String INDICE_DIMENSAO_USUARIOS = "CREATE INDEX "
            + NOME_INDEX_USUARIOS + " ON "+NOME_TABELA_USUARIOS+" (idusuario ASC)";
    
    public static final String DROP_INDICE_DIMENSAO_USUARIOS = "DROP INDEX "
            + NOME_INDEX_USUARIOS + " ON "+NOME_TABELA_USUARIOS;
            
    public static final String TABELA_FATO_HISTORICO = "CREATE TABLE IF NOT "
            + "EXISTS "+NOME_TABELA_HISTORICOS+" ("
            + "idhistorico INT UNSIGNED NOT NULL, "
            + "idusuario VARCHAR(15) NOT NULL, "
            + "idtitulo VARCHAR(15) NOT NULL, "
            + "dataemprestimo DATETIME NOT NULL, "
            + "dataprevisaodevolucao DATETIME NOT NULL, "
            + "datadevolucao DATETIME NOT NULL, "
            + "valorpago DECIMAL(10,2) NOT NULL, "
            + "valormulta DECIMAL(10,2) NOT NULL, "
            + "pagamento VARCHAR(1) NOT NULL, "
            + "md5 VARCHAR(32) NOT NULL, "
            + "PRIMARY KEY (idhistorico), "
            + "INDEX fk_historico_idusuario_idx (idusuario ASC), "
            + "INDEX fk_historico_idtitulo_idx (idtitulo ASC), "
            + "CONSTRAINT fk_historico_idusuario "
            + "FOREIGN KEY (idusuario) "
            + "REFERENCES "+NOME_TABELA_USUARIOS+" (idusuario) "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION, "
            + "CONSTRAINT fk_historico_idtitulo "
            + "FOREIGN KEY (idtitulo) "
            + "REFERENCES "+NOME_TABELA_TITULOS+" (idtitulo) "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION "
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
    
    public static final String INDICE_FATO_HISTORICOS = "CREATE INDEX "
            + NOME_INDEX_HISTORICOS + " ON "+NOME_TABELA_HISTORICOS+" (idhistorico ASC)";
    
    public static final String DROP_INDICE_FATO_HISTORICOS = "DROP INDEX "
            + NOME_INDEX_HISTORICOS + " ON "+NOME_TABELA_HISTORICOS;
    
    public static final String TABELA_DIMENSAO_TITULOS = "CREATE TABLE IF NOT "
            + "EXISTS "+NOME_TABELA_TITULOS+" ("
            + "idtitulo VARCHAR(15) NOT NULL, "
            + "titulo VARCHAR(450) NOT NULL, "
            + "md5 VARCHAR(32) NOT NULL, "
            + "PRIMARY KEY (idtitulo)) ENGINE=InnoDB DEFAULT CHARSET=utf8";

    public static final String INDICE_DIMENSAO_TITULOS = "CREATE INDEX "
            + NOME_INDEX_TITULOS + " ON "+NOME_TABELA_TITULOS+" (idtitulo ASC)";
    
    public static final String DROP_INDICE_DIMENSAO_TITULOS = "DROP INDEX "
            + NOME_INDEX_TITULOS + " ON "+NOME_TABELA_TITULOS;
}
