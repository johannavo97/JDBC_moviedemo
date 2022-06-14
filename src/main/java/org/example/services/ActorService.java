package org.example.services;

import org.example.dao.ActorI;
import org.example.models.Actor;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ActorService implements ActorI {
    @Override
    public Actor save(Actor a) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(a);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return a;
    }

    @Override
    public Optional<Actor> findById(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Optional<Actor> a = Optional.ofNullable(s.find(Actor.class, id));
        s.close();
        return a;
    }

    @Override
    public List<Actor> findAllBornAfter(int year) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Actor> a = s.createQuery("SELECT a FROM actors a WHERE a.yearOfBirth > :year",Actor.class).setParameter("year", year).getResultList();
        s.close();
        return a;
    }

    @Override
    public List<Actor> findAllWithLastNameEndsWith(String lastNameEndsWith) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Actor> a = s.createQuery("SELECT a FROM actors a WHERE a.lastName LIKE :lastName",Actor.class).setParameter("lastName", "%" + lastNameEndsWith).getResultList();
        s.close();
        return a;
    }
}
