package itmo.polikiss.dao;

import itmo.polikiss.models.Owner;
import org.hibernate.Session;

import java.util.List;

public interface Dao <T>{
    T findById(int id);

    void save(T model, Session session);

    void update(T model, Session session);
    void delete(T model, Session session);
    Session getSession();
    void closeSession(Session session);

    List<T> findAll();
}
