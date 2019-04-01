package cz.tul;

import cz.tul.data.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
public class Main {

    @Bean
    public CityDao cityDao() {return new CityDao();}

    @Bean
    public StateDao stateDao() {return  new StateDao();}

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        StateDao stateDao = ctx.getBean(StateDao.class);
        CityDao cityDao = ctx.getBean(CityDao.class);

        List<City> cities = cityDao.getAllCities();
        System.out.println(cities);

        List<State> states = stateDao.getAllStates();
        System.out.println(states);


        //State s = new State("France");
        //stateDao.create(s);

        //City c = new City("Louvre", s);
        //cityDao.create(c);

    }

}