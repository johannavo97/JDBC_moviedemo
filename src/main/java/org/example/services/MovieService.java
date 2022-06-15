package org.example.services;

import lombok.extern.java.Log;
import org.example.dao.MovieI;
import org.example.models.Movie;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Log
public class MovieService implements MovieI {
    @Override
    public Movie createMovie(Movie m) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(m);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return m;
    }

    @Override
    public boolean deleteMovie(Movie m) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.delete(m);
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
    public List<Movie> findAllByTitle(String title) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Movie> m = s.createQuery("SELECT m FROM movies m WHERE m.title = :title",Movie.class).setParameter("title",title).getResultList();
        s.close();
        return m;
    }

    @Override
    public Optional<Movie> findById(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Optional<Movie> m = Optional.ofNullable(s.find(Movie.class, id));
        s.close();
        return m;
    }

    @Override
    public List<Movie> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Movie> m = s.createQuery("from movies ",Movie.class).getResultList();
        s.close();
        return m;
    }

    @Override
    public List<Movie> findAllWithActors() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Movie> m = s.createQuery("SELECT m FROM movies m LEFT JOIN fetch m.actors",Movie.class).getResultList();
        s.close();
        return m;
    }
}
