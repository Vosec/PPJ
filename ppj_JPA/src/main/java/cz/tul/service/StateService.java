package cz.tul.service;

import cz.tul.data.State;
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

    public boolean exists(String statename) {
        return stateRepository.exists(statename);
    }

    public List<State> getAllStates() {
        return StreamSupport.stream(stateRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteStates() {
        stateRepository.deleteAll();
    }
}



