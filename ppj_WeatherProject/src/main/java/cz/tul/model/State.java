package cz.tul.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="states")
public class State {

    @Id
    @Column(name="statename")
    private String name;

    public State(String name) {
        this.name = name;
    }

    public State() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(name, state.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "State: " + name;
    }
}
