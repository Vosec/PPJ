package cz.tul.data;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CityDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<City> getCitiesByState(String statename) {
        Criteria crit = session().createCriteria(City.class);

        crit.createAlias("state", "s");
        crit.add(Restrictions.eq("s.stateName", statename));

        return crit.list();
    }
    @SuppressWarnings("unchecked")
    public List<City> getAllCities() {
        Criteria crit = session().createCriteria(City.class);
        return crit.list();
    }

    public void saveOrUpdate(City city) {
        session().saveOrUpdate(city);
    }


    public boolean delete(int id) {
        Query query = session().createQuery("delete from City where id=:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public City getCity(int id) {
        Criteria crit = session().createCriteria(City.class);
        crit.add(Restrictions.idEq(id));

        return (City) crit.uniqueResult();
    }

    public void deleteCities() {
        session().createQuery("delete from City").executeUpdate();
    }

}
