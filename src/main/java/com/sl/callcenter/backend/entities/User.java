/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.callcenter.backend.entities;

import com.sl.callcenter.backend.enumerados.user.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.Date;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;


/**
 *
 * @author sicemal
 */
@Entity
@Table(name = "sl_user")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 64, nullable = false, unique = true)
    private String login;
    
    @XmlTransient
    @Column(length = 128, nullable = false)
    private String password;
    
    @Column(length = 64, nullable = false)
    private String name;
    
    @Column(nullable = false)
    private boolean active;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 16, nullable = false)
    private Type type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", name=" + name + ", active=" + active + ", createdAt=" + createdAt + ", type=" + type + '}';
    }
    
    
}
