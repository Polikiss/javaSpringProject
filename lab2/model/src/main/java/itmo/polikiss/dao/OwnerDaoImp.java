package itmo.polikiss.dao;

import itmo.polikiss.models.Owner;
import itmo.polikiss.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OwnerDaoImp implements OwnerDao{
    final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    public Owner findById(int id) {
        return session.get(Owner.class, id);
    }


    @Override
    public void save(Owner owner, Session session) {
        session.persist(owner);
    }
    @Override
    public void update(Owner owner, Session session) {
        session.merge(owner);
    }
    @Override
    public void delete(Owner owner, Session session) {
        session.remove(owner);
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void closeSession(Session session) {
        session.close();
    }

    @Override
    public List<Owner> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Owner> criteria = builder.createQuery(Owner.class);
        criteria.from(Owner.class);
        List<Owner> data = session.createQuery(criteria).getResultList();
        return data;
    }
}



