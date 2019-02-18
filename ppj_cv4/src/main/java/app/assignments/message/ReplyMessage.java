package app.assignments.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pingMessageReply")
public class ReplyMessage implements Message {

    @Autowired
    @Qualifier("pingMessage")
    private Message original;

    @Value("ahoj")
    private String reply;

    /**
    public ReplyMessage(Message original, String reply) {
        this.original = original;
        this.reply = reply;
    }
    */
    public String getSender() {
        return original.getRecipient();
    }

    public String getRecipient() {
        return original.getSender();
    }

    public String getText() {
        return "ORIGINAL:\n" + original.getText() + "\nREPLY:\n" + reply;
    }
}
