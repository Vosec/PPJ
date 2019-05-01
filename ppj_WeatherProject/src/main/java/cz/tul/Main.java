package cz.tul;

import cz.tul.config.readonlysetup.ReadOnlyInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


@SpringBootApplication
@EnableJpaRepositories("cz.tul.repositories")
@EntityScan("cz.tul.model")
public class Main {

    //for periodic API calls for all city IDs from DB
    //https://www.baeldung.com/spring-task-scheduler
    @Bean
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    ReadOnlyInterceptor readOnlyInterceptor(){return new ReadOnlyInterceptor();}

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}