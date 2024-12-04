/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.callcenter.backend.jdbc.dao;

import com.sl.hello.backend.entities.Call;
import com.sl.callcenter.backend.enumerados.call.Status;
import com.sl.hello.backend.infra.ConexaoJDBC;
import com.sl.hello.backend.infra.ConexaoPostgresJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sicemal
 */
public class CallDAO {
    
    private final ConexaoJDBC connectionJDBC;
    
    public CallDAO() throws ClassNotFoundException, SQLException {
        this.connectionJDBC = new ConexaoPostgresJDBC();
    }
    
    public Long insert(Call call) throws SQLException {
        Long id = null;
        String sql = "INSERT INTO sl_call(issue, status, message) VALUES (?, ?, ?) RETURNING id";
        
        try {
            PreparedStatement stmt = this.connectionJDBC.getConnection().prepareStatement(sql);
            stmt.setString(1, call.getIssue());
            stmt.setString(2, call.getStatus().toString());
            stmt.setString(3, call.getMessage());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getLong("id");
            }
            this.connectionJDBC.commit();
        } catch (SQLException ex) {
            Logger.getLogger(CallDAO.class.getName()).log(Level.SEVERE, null, ex);
            this.connectionJDBC.rollback();
            throw ex;
        }
        return id;
    }
    
    public int update(Call call) throws SQLException {
        Long id = null;
        String sql = "UPDATE sl_call SET issue = ?, status = ?, message = ? WHERE id = ?";
        int rowsAffected = 0;
        try {
            PreparedStatement stmt = this.connectionJDBC.getConnection().prepareStatement(sql);
            stmt.setString(1, call.getIssue());
            stmt.setString(2, call.getStatus().toString());
            stmt.setString(3, call.getMessage());
            stmt.setLong(4, call.getId());
            rowsAffected = stmt.executeUpdate();
            this.connectionJDBC.commit();
        } catch (SQLException ex) {
            Logger.getLogger(CallDAO.class.getName()).log(Level.SEVERE, null, ex);
            this.connectionJDBC.rollback();
            throw ex;
        }
        return rowsAffected;
        
    }
            
    public int delete(long id) throws SQLException {
        String sql = "DELETE FROM sl_call WHERE id = ?";
        int rowsAffected = 0;
        try {
            PreparedStatement stmt = this.connectionJDBC.getConnection().prepareStatement(sql);
            stmt.setLong(1, id);
            rowsAffected = stmt.executeUpdate();
            this.connectionJDBC.commit();
        } catch (SQLException ex) {
            Logger.getLogger(CallDAO.class.getName()).log(Level.SEVERE, null, ex);
            this.connectionJDBC.rollback();
            throw ex;
        }
        return rowsAffected;
    }
    
    public Call select(long id) throws SQLException {
        String sql = "SELECT * FROM sl_call WHERE id = ?";
        try {
            PreparedStatement stmt = this.connectionJDBC.getConnection().prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CallDAO.class.getName()).log(Level.SEVERE, null, ex);
            this.connectionJDBC.rollback();
            throw ex;
        }
        return null;
    }
    
    public List<Call> list() throws SQLException {
        String sql = "SELECT * FROM sl_call ORDER BY id";
        try {
            PreparedStatement stmt = this.connectionJDBC.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Call> calls = new ArrayList<>();
            while (rs.next()) {
                calls.add(parser(rs));
            }
            return calls;
        } catch (SQLException ex) {
            Logger.getLogger(CallDAO.class.getName()).log(Level.SEVERE, null, ex);
            this.connectionJDBC.rollback();
            throw ex;
        }
    }
    
    private Call parser(ResultSet resultSet) throws SQLException {
        Call c = new Call();
        c.setId(resultSet.getLong("id"));
        c.setIssue(resultSet.getString("issue"));
        c.setMessage(resultSet.getString("message"));
        c.setStatus(Status.valueOf(resultSet.getString("status")));
        return c;
    }

}
