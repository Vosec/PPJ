package cz.tul;

import cz.tul.data.*;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class Main {

    @Bean
    public CityDao cityDao() {return new CityDao();}

    @Bean
    public StateDao stateDao() {return  new StateDao();}


    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        StateDao stateDao = ctx.getBean(StateDao.class);
        CityDao cityDao = ctx.getBean(CityDao.class);

        List<City> cities = cityDao.getCities();
        System.out.println(cities);

        //List<State> states = stateDao.getAllStates();
        //System.out.println(states);


        //State s = new State("France");
        //stateDao.create(s);

        //City c = new City("Louvre", s);
        //cityDao.create(c);

    }

}