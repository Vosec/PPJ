package app.assignments.writer;

import app.assignments.message.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListWriter implements Writer {

    private List<Message> buffer = new ArrayList<>();

    public void write(Message message) {
        buffer.add(message);
    }

    public List<Message> listWrittenMessages() {
        return Collections.unmodifiableList(buffer);
    }
}
