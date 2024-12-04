/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sl.hello.backend.infra;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sicemal
 */
public interface ConexaoJDBC {
    public Connection getConnection();
    public void close();
    public void commit() throws SQLException;
    public void rollback();

}
