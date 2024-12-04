/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.hello.backend.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sicemal
 */
public class ConexaoPostgresJDBC implements ConexaoJDBC {

    private Connection connection = null;
    
    public ConexaoPostgresJDBC() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "postgres");
        
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", properties);
        this.connection.setAutoCommit(false);
        
    }
    
    public Connection getConnection() {
        return this.connection;
    }

    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void commit() throws SQLException {
        this.connection.commit();
    }

    public void rollback() {
        try {
            this.connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
