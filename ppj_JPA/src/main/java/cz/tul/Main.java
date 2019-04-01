package cz.tul;

import cz.tul.data.*;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("cz.tul.repositories")
@EntityScan("cz.tul.data")
public class Main {

    @Bean
    public CityService cityService() {return new CityService();}

    @Bean
    public StateService stateService() {return  new StateService();}

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        StateService stateService = ctx.getBean(StateService.class);
        CityService cityService = ctx.getBean(CityService.class);

        List<City> cities = cityService.getCities();
        System.out.println(cities);

        List<State> states = stateService.getAllStates();
        System.out.println(states);


        //State s = new State("France");
        //stateDao.create(s);

        //City c = new City("Louvre", s);
        //cityDao.create(c);

    }

}