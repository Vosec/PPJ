package app.assignments.configuration;

import app.assignments.writer.ListWriter;
import app.assignments.writer.StdoutWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfig {
    @Bean
    public ListWriter listWriter() {
        return new ListWriter();
    }
    @Bean
    public StdoutWriter stdoutWriter() {
        return new StdoutWriter();
    }
}
