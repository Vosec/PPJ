package cz.tul.controllers.web;

import cz.tul.model.State;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HomeController {
    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @RequestMapping(value = "/")
    public String showHome(Model model){
        List<State> states = stateService.getAllStates();
        model.addAttribute("states", states);
        return "home";
    }
}
