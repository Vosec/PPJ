package cz.tul;

import cz.tul.Config.ReadOnlySetup.ReadOnlyInterceptor;
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

    //pro periodické tahání dat z API pro všechna města uložená v DB
    //https://www.baeldung.com/spring-task-scheduler
    @Bean
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    ReadOnlyInterceptor readOnlyInterceptor(){return new ReadOnlyInterceptor();}

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);



        //SpringApplication app = new SpringApplication(Main.class);
        //ApplicationContext ctx = app.run(args);
        //zkouška načtení všech měření pro všechny uložená města
        //DownloadService d = ctx.getBean(DownloadService.class);
        //d.work();
    }

}