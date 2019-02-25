package app.assignments.app;

import app.assignments.configuration.AppConfig;
import app.assignments.message.Message;
import app.assignments.writer.ListWriter;
import app.assignments.writer.Writer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Message ping = (Message) context.getBean("pingMessage");
        Message hello = (Message) context.getBean("helloMessage");
        Message pingReply = (Message) context.getBean("pingMessageReply");

        Writer stdoutWriter = (Writer) context.getBean("stdoutWriter");
        Writer listWriter = (Writer) context.getBean("listWriter");

        stdoutWriter.write(ping);
        stdoutWriter.write(hello);
        stdoutWriter.write(pingReply);


        listWriter.write(ping);
        ((ListWriter) listWriter).listWrittenMessages().forEach(stdoutWriter::write);

    }

}
