package app.assignments.configuration;

import app.assignments.message.PingMessage;
import app.assignments.message.ReplyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan("app.assignments")
@Import({MessageConfig.class, WriterConfig.class})
public class AppConfig {

    @Autowired
    public PingMessage pingMessage;

    @Bean
    public ReplyMessage replyMessage(){
        return new ReplyMessage(pingMessage,"ahoj");
    }
}
