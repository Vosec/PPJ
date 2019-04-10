package cz.tul;

import cz.tul.model.*;
import cz.tul.repositories.CityRepository;
import cz.tul.repositories.MeasurementRepository;
import cz.tul.service.CityService;
import cz.tul.service.MeasurementService;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("cz.tul.repositories")
@EntityScan("cz.tul.model")
public class Main {



    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        Date d = new Date();
        MeasurementRepository mRepo = ctx.getBean(MeasurementRepository.class);

        Measurement m1 = new Measurement(27,11,10,"Praha", d);





        //mService.addMeasurement(m1);


        List<Measurement> m = mRepo.findByCityName("Praha");
        System.out.println(m);









    }

}