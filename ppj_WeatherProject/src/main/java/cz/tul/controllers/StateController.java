package cz.tul.controllers;

import cz.tul.api.RestApi;
import cz.tul.model.State;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StateController {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @RequestMapping(value = RestApi.STATES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<State>> getStates() {
        List<State> states = stateService.getAllStates();

        return new ResponseEntity<>(states, HttpStatus.OK);
    }


}
