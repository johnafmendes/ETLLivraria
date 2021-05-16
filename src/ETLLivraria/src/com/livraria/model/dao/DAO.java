package com.livraria.model.dao;

import com.livraria.constantes.Constantes;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 * Classe base das classes de acesso a dados
 */
public abstract class DAO {

    /**
     * Objeto que contém as propriedades de conexão com o banco de dados
     */
    private static Properties props;
    /**
     * Conexão com o banco de dados
     */
    private static Connection connOrigem;
    private static Connection connDestino;
    /**
     * Statement utilizado para executar queries
     */
    private PreparedStatement stmtOrigem;
    private PreparedStatement stmtDestino;
//    private Driver driver2;
//    private Vector freeConnection;
//    private int maxConnection;
//    private int count;

    static {
        // Carrega as propriedades de conexão do arquivo de configuração
        props = new Properties();
        try {
            props.load(new FileInputStream("db.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Construtor protegido
     */
    protected DAO() {
    }

//    public void DatabaseConnectionPool(String drivername, String conUrl,
//            String conuser, String conpassword) throws SQLException {
//        freeConnection = new Vector();
//        try {
//            driver2 = (Driver) Class.forName(drivername).newInstance();
//            DriverManager.registerDriver(driver2);
//        } catch (Exception _ex) {
//            new SQLException();
//        }
//        count = 0;
//        maxConnection = 5;
//    }

//    public void destroy() {
//        closeAll();
//        try {
//            DriverManager.deregisterDriver(driver2);
//            return;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//    }

//    public synchronized void freeConnection(Connection connection) {
//        freeConnection.addElement(connection);
//        count--;
//        notifyAll();
//    }

//    private synchronized void closeAll() {
//        for (Enumeration enumeration = freeConnection.elements(); enumeration
//                .hasMoreElements();) {
//            Connection connection = (Connection) enumeration.nextElement();
//            try {
//                connection.close();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        freeConnection.removeAllElements();
//    }

//    public synchronized Connection getConnection() {
//        Connection connection = null;
//        if (freeConnection.size() > 0) {
//            connection = (Connection) freeConnection.elementAt(0);
//            freeConnection.removeElementAt(0);
//            try {
//                if (connection.isClosed()) {
//                    connection = getConnection();
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                connection = getConnection();
//            }
//            return connection;
//        }
//        if (count < maxConnection) {
//            connection = newConnection();
//            System.out.println("NEW CONNECTION CREATED");
//        }
//        if (connection != null) {
//            count++;
//        }
//        return connection;
//    }

//    private Connection newConnection() {
//        Connection connection = null;
//        try {
////            connection = DriverManager.getConnection(url, user, password);
//        } catch (Exception e) {
//            print(e.getMessage());
//            return null;
//        }
//        return connection;
//    }
//
//    private void print(String print) {
//        System.out.println(print);
//    }

    /**
     * Obtém uma conexão com o banco de dados
     *
     * @return Conexão aberta
     * @throws DAOException
     */
    protected Connection getConnectionOrigem() throws DAOException {
        try {
            if (connOrigem == null) {
                // Se uma conexão não existir, cria
                String driver = props.getProperty("db_driver_origem");
                String url = props.getProperty("db_url_origem");
                String user = props.getProperty("db_user_origem");
                String password = props.getProperty("db_password_origem");
                Class.forName(driver);
                connOrigem = DriverManager.getConnection(url, user, password);
                return connOrigem;
            }
            return connOrigem;
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException();
        }
    }

    /**
     * Obtém uma conexão com o banco de dados
     *
     * @return Conexão aberta
     * @throws DAOException
     */
    protected Connection getConnectionDestino() throws DAOException {
        try {
            if (connDestino == null) {
                // Se uma conexão não existir, cria
                String driver = props.getProperty("db_driver_destino");
                String url = props.getProperty("db_url_destino") + Constantes.BANCO;
                String user = props.getProperty("db_user_destino");
                String password = props.getProperty("db_password_destino");
                Class.forName(driver);
                connDestino = DriverManager.getConnection(url, user, password);
                return connDestino;
            }
            return connDestino;
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Obtém um PreparedStatement
     *
     * @param sql Query a ser executada
     * @return PreparedStatement criado
     * @throws DAOException
     */
    private PreparedStatement getStatementOrigem(String sql, boolean autoCommit) throws DAOException {
        try {
            if (stmtOrigem == null) {
                // Cria o PreparedStatement
                //MySQL
                stmtOrigem = getConnectionOrigem().prepareStatement(sql, new String[]{"id"});
                //PostgreSQL
//                stmt = getConnection().prepareStatement(sql, 
//                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
//                        ResultSet.CONCUR_READ_ONLY,
//                        Statement.RETURN_GENERATED_KEYS);
            }
            connOrigem.setAutoCommit(autoCommit);
            return stmtOrigem;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Obtém um PreparedStatement
     *
     * @param sql Query a ser executada
     * @return PreparedStatement criado
     * @throws DAOException
     */
    private PreparedStatement getStatementDestino(String sql, boolean autoCommit) throws DAOException {
        try {
            if (stmtDestino == null) {
                // Cria o PreparedStatement
                //MySQL
                stmtDestino = getConnectionDestino().prepareStatement(sql, new String[]{"id"});
                //PostgreSQL
//                stmt = getConnection().prepareStatement(sql, 
//                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
//                        ResultSet.CONCUR_READ_ONLY,
//                        Statement.RETURN_GENERATED_KEYS);
            }
            connDestino.setAutoCommit(autoCommit);
            return stmtDestino;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Fecha o PreparedStatement e a conexão
     *
     * @throws DAOException
     */
    protected void cleanUpOrigem() throws DAOException {
        try {
            if (stmtOrigem != null) {
                stmtOrigem.close();
                stmtOrigem = null;
            }
//            if (connOrigem != null) {
//                connOrigem.close();
//                connOrigem = null;
//            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Fecha o PreparedStatement e a conexão
     *
     * @throws DAOException
     */
    protected void cleanUpDestino() throws DAOException {
        try {
            if (stmtDestino != null) {
                stmtDestino.close();
                stmtDestino = null;
            }
//            if (connDestino != null) {
//                connDestino.close();
//                connDestino = null;
//            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Executa uma query do tipo SELECT
     *
     * @param sql Query a ser executada
     * @param params Parâmetros a serem utilizados na query
     * @return ResultSet criado
     * @throws DAOException
     */
    protected ResultSet executeQueryOrigem(String sql, Object[] params) throws DAOException {
        try {
            return getPopulatedStatementOrigem(sql, params, true).executeQuery();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Executa uma query do tipo SELECT
     *
     * @param sql Query a ser executada
     * @param params Parâmetros a serem utilizados na query
     * @return ResultSet criado
     * @throws DAOException
     */
    protected ResultSet executeQueryDestino(String sql, Object[] params) throws DAOException {
        try {            
            return getPopulatedStatementDestino(sql, params, true).executeQuery();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Executa uma query do tipo UPDATE, INSERT ou DELETE
     *
     * @param sql Query a ser executada
     * @param params Parâmetros a serem utilizados na query
     * @return Número de registros afetados pela query
     * @throws DAOException
     */
    protected int executeUpdateOrigem(String sql, Object[] params, boolean autoCommit, boolean commit) throws DAOException {
        try {
            PreparedStatement stmt = getPopulatedStatementOrigem(sql, params, autoCommit);

            int result = stmt.executeUpdate();
            if(commit){
                connOrigem.commit();
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Executa uma query do tipo UPDATE, INSERT ou DELETE
     *
     * @param sql Query a ser executada
     * @param params Parâmetros a serem utilizados na query
     * @return Número de registros afetados pela query
     * @throws DAOException
     */
    protected int executeUpdateDestino(String sql, Object[] params, boolean autoCommit, boolean commit) throws DAOException {
        try {
            PreparedStatement stmt = getPopulatedStatementDestino(sql, params, autoCommit);
            int result = stmt.executeUpdate();
            if(commit){
                connDestino.commit();
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Obtém um PreparedStatement já populado com os parâmetros da query
     *
     * @param sql Query a ser executada
     * @param params Parâmetros a serem utilizados na query
     * @return PreparedStatement criado
     * @throws DAOException
     */
    private PreparedStatement getPopulatedStatementOrigem(String sql, Object[] params, boolean autoCommit) throws DAOException {
        try {
            PreparedStatement stmt = getStatementOrigem(sql, autoCommit);

            int count = 1;

            // Itera sobre os parâmetros, inserindo-os dentro do PreparedStatement
            if (params != null) {
                for (Object param : params) {
                    if (param != null && param instanceof String) {
                        // Se o parâmetro for uma String, remove os espaços em branco
                        param = ((String) param).trim();
                    }
//                    System.out.println(stmt.toString());
                    stmt.setObject(count++, param);
//                    System.out.println(stmt.toString());
                }
            }
            System.out.println(stmt.toString());
            return stmt;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Obtém um PreparedStatement já populado com os parâmetros da query
     *
     * @param sql Query a ser executada
     * @param params Parâmetros a serem utilizados na query
     * @return PreparedStatement criado
     * @throws DAOException
     */
    private PreparedStatement getPopulatedStatementDestino(String sql, Object[] params, boolean autoCommit) throws DAOException {
        try {
            PreparedStatement stmt = getStatementDestino(sql, autoCommit);

            int count = 1;

            // Itera sobre os parâmetros, inserindo-os dentro do PreparedStatement
            if (params != null) {
                for (Object param : params) {
                    if (param != null && param instanceof String) {
                        // Se o parâmetro for uma String, remove os espaços em branco
                        param = ((String) param).trim();
                    }
//                    System.out.println(stmt.toString());
                    stmt.setObject(count++, param);
//                    System.out.println(stmt.toString());
                }
            }
            System.out.println(stmt.toString());
            return stmt;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected int numeroRegistros(ResultSet rs) throws DAOException {
        int qtd = 0;
        try {
            rs.beforeFirst();
            while (rs.next()) {
                qtd++;
            }
            rs.beforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qtd;
    }
}
