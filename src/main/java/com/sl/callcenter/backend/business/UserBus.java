/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.callcenter.backend.business;

import com.sl.callcenter.backend.entities.User;
import com.sl.callcenter.backend.enumerados.user.Type;
import com.sl.callcenter.backend.infra.HibernateUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author sicemal
 */
public class UserBus {
    
    public static User selectUser() {
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        
        User student = null;
        Transaction transaction = session.beginTransaction();
        try {
            // TODO REMOVER - METODO TEMPORARIO
            student = session.createQuery("from User where login= :login", User.class)
                    .setParameter("login", "aluno")
                    .getSingleResult();
            transaction.commit();
            return student;
        } catch (Exception e) {
            Logger.getLogger(UserBus.class.getCanonicalName()).log(Level.SEVERE, null, e);
        }
        if (student == null) {
            student = new User();
            student.setLogin("aluno");
            student.setPassword(DigestUtils.sha256Hex("123"));
            student.setName("Aluno Teste");
            student.setType(Type.SUPORT);
            student.setCreatedAt(new Date());
            student.setActive(true);

            UserBus userBus = new UserBus();
            userBus.insert(student);
            transaction.commit();
        }
        return student;
        
        
    }

    private Long insert(User student) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.persist(student);
        t.commit();
        return student.getId();
    }
    
    public User select(long id) {
        return (User) HibernateUtil.getSessionFactory()
                .openSession()
                .get(User.class, id);
    }
}
