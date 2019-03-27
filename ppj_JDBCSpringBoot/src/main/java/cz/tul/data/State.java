package cz.tul.data;

import java.util.Objects;

public class State {
    private String stateName;

    public State(String stateName) {
        this.stateName = stateName;
    }

    public State() {
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return Objects.equals(getStateName(), state.getStateName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStateName());
    }

    @Override
    public String toString() {
        return "State: " + stateName;
    }
}
