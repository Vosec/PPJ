package app.assignments.configuration;

import app.assignments.message.CustomMessage;
import app.assignments.message.PingMessage;
import app.assignments.message.ReplyMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    @Bean
    public CustomMessage helloMessage(){
        return new CustomMessage();
    }
    @Bean
    public PingMessage pingMessage () {
        return new PingMessage();
    }
    @Bean
    public ReplyMessage pingMessageReply (){
        return new ReplyMessage(pingMessage(), "ahoj");
    }

}
