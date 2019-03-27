package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class StateDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(State state) {
        session().save(state);
    }

    public boolean exists(String statename) {
        Criteria crit = session().createCriteria(State.class);
        crit.add(Restrictions.idEq(statename));
        State state = (State) crit.uniqueResult();
        return state != null;
    }

    @SuppressWarnings("unchecked")
    public List<State> getAllStates() {
        Criteria crit = session().createCriteria(State.class);
        return crit.list();
    }

    public void deleteStates() {
        session().createQuery("delete from State").executeUpdate();
    }
}



