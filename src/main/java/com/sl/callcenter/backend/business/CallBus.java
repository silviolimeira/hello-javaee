/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.callcenter.backend.business;

import com.sl.callcenter.backend.entities.Call;
import com.sl.callcenter.backend.enumerados.call.Status;
import com.sl.callcenter.backend.enumerados.call.Type;
import com.sl.callcenter.backend.infra.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author sicemal
 */
public class CallBus {
    
    public Long insert(Call call) {

        call.setCreatedAt(new Date());
        call.setStatus(Status.NEW);
        call.setType(Type.REQUEST);
        call.setUser(UserBus.selectUser());
        call.setUserStatus(UserBus.selectUser());
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.persist(call);
        t.commit();
        return call.getId();

    }
    
    public void update(Call call) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(call);
        t.commit();

    }
    
    public void delete(long id) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        Call c = this.select(id);
        
        Transaction t = s.beginTransaction();
        s.remove(c);
        t.commit();
    }

    public Call select(long id) {
        return (Call) HibernateUtil.getSessionFactory()
            .openSession()
            .get(Call.class, id);    
    }

    public List<Call> list() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        
        Transaction transaction = session.beginTransaction();
        List<Call> calls = session.createQuery("from Call", Call.class)
            .list();
        transaction.commit();
        // TODO REMOVER - METODO TEMPORARIO
        return calls;
    }
    
    
}
