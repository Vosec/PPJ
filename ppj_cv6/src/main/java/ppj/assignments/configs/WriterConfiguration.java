package ppj.assignments.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import ppj.assignments.writer.Writer;

public class WriterConfiguration {

    @Configuration
    @PropertySource("classpath:application-devel.properties")
    @Profile("devel")
    public static class DevelWriter {
        @Bean
        public Writer writer() {
            return new ppj.assignments.writer.DevelWriter();
        }
    }

    @Configuration
    @PropertySource("classpath:application-prod.properties")
    @Profile("prod")
    public static class ProdWriter {
        @Bean
        public Writer writer(){
            return new ppj.assignments.writer.ProdWriter();
        }

    }

}
