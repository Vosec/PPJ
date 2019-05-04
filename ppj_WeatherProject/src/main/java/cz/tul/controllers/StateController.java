package cz.tul.controllers;

import cz.tul.api.RestApi;
import cz.tul.helper.InputHelper;
import cz.tul.model.State;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import cz.tul.helper.InputHelper;

@RestController
public class StateController {

    private StateService stateService;
    private InputHelper ih = new InputHelper();

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @RequestMapping(value = RestApi.STATES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<State>> getStates() {
        List<State> states = stateService.getAllStates();

        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @RequestMapping(value = RestApi.STATES_PATH, method = RequestMethod.POST)
    public ResponseEntity<State> createState(@RequestBody State state) {
        if (stateService.exists(state.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (state.getName().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (ih.isValidName(state.getName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            stateService.create(state);
            return new ResponseEntity<>(state, HttpStatus.OK);
        }
    }

    @RequestMapping(value = RestApi.STATE_PATH, method = RequestMethod.GET)
    public ResponseEntity<State> getState(@PathVariable("stateName") String name) {
        State state = stateService.get(name);
        if (state != null) {
            return new ResponseEntity<>(state, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = RestApi.STATE_PATH, method = RequestMethod.POST)
    public ResponseEntity<State> updateState(@RequestBody State state) {
        State requestedState = stateService.get(state.getName());
        if (requestedState != null) {
            stateService.deleteState(requestedState);
            State updatedState = stateService.save(state);
            return new ResponseEntity<>(updatedState, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = RestApi.STATE_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<State> deleteState(@PathVariable("stateName") String name) {
        State state = stateService.get(name);
        if (state != null) {
            stateService.deleteState(state);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
