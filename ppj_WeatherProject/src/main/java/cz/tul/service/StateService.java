package cz.tul.service;

import cz.tul.model.State;
import cz.tul.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public void create(State state) {
        stateRepository.save(state);
    }

    public State save(State state) {
        return stateRepository.save(state);
    }

    public State get(String name){
        return stateRepository.findOne(name);
    }

    public boolean exists(String name) {
        return stateRepository.exists(name);
    }

    public List<State> getAllStates() {
        return StreamSupport.stream(stateRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteStates() {
        stateRepository.deleteAll();
    }

    public void deleteState(State state){stateRepository.delete(state);}
}



