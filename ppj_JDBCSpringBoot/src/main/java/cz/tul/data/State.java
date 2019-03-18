package cz.tul.data;

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
    public String toString() {
        return "State: " + stateName;
    }
}
