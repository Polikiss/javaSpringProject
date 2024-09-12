package itmo.polikiss.dao;

import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import itmo.polikiss.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class KittyDaoImp implements KittyDao{

    private final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    @Override
    public Kitty findById(int id) {
        return session.get(Kitty.class, id);
    }

    @Override
    public void save(Kitty model, Session session) {
        session.persist(model);
    }

    @Override
    public void update(Kitty model, Session session) {
        session.merge(model);
    }

    @Override
    public void delete(Kitty model, Session session) {
        session.remove(model);
    }

    @Override
    public List<Kitty> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Kitty> criteria = builder.createQuery(Kitty.class);
        criteria.from(Kitty.class);
        return session.createQuery(criteria).getResultList();
    }

    public Session getSession(){
        return session;
    }

    @Override
    public void closeSession(Session session) {
        session.close();
    }
}
