package cz.tul.controllers.web;

import cz.tul.model.State;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        State newState = new State();
        newState.setStateName("");

        model.addAttribute("states", states);
        model.addAttribute("newState", newState);
        return "home";
    }
    @RequestMapping(value = "/createState", method= RequestMethod.POST)
    public String createState(HttpServletRequest request, @ModelAttribute(value="newState") State state) {
        stateService.create(state);
        String referer = request.getHeader("Referer");
        //vrátí se na předchozí stránku po vytvoření nového státu
        return "redirect:"+ referer;
    }

    @RequestMapping(value = "/deleteState/{state}", method= RequestMethod.POST)
    public String deleteState(HttpServletRequest request, @PathVariable("state") State state) {
        stateService.deleteState(state);
        String referer = request.getHeader("Referer");
        //vrátí se na předchozí stránku po vytvoření nového státu
        return "redirect:"+ referer;
    }
}
