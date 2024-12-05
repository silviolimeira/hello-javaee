/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.callcenter.backend.entities;

import com.sl.callcenter.backend.enumerados.call.Status;
import com.sl.callcenter.backend.enumerados.call.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sicemaḉl
 */
@Entity
@Table(name = "sl_call")
public class Call implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 16, nullable = false)
    private Type type;
    
    // TODO 13
    //https://www.youtube.com/watch?v=y-kdUI9bL8Y&list=PL1NdiP2jsLnuEqNkOF7-ISTciRrKd51Hv&index=13
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;
    
    @Column(length = 64, nullable = false)
    private String issue;
    
    @Column(length = 2048, nullable = false)
    private String message;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 8, nullable = false)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_status", nullable = false)
    private User userStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(User userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Call other = (Call) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Call{" + "id=" + id + ", createdAt=" + createdAt + ", type=" + type + ", user=" + user + ", issue=" + issue + ", message=" + message + ", status=" + status + ", userStatus=" + userStatus + '}';
    }
    
}
