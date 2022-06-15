package org.example.services;

import lombok.extern.java.Log;
import org.example.dao.GenreI;
import org.example.models.Genre;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Log
public class GenreService implements GenreI {
    @Override
    public Genre createGenre(Genre g) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(g);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return g;
    }

    @Override
    public boolean deleteGenre(Genre g) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.remove(g);
            tx.commit();
            return true;
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return false;
    }

    @Override
    public List<Genre> findAllByName(String name) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Genre> g = s.createQuery("SELECT g FROM genres WHERE name = :name",Genre.class).setParameter("name", name).getResultList();
        s.close();
        return g;
    }

    @Override
    public Optional<Genre> findById(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Optional<Genre> g = Optional.ofNullable(s.find(Genre.class, id));
        s.close();
        return g;
    }

    @Override
    public List<Genre> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Genre> g = s.createQuery("from genres ", Genre.class).getResultList();
        s.close();
        return g;
    }
}
