package cz.tul.controllers.web;

import cz.tul.helper.InputHelper;
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
    private InputHelper ih = new InputHelper();

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }


    @RequestMapping(value = "/")
    public String showHome(Model model){
        List<State> states = stateService.getAllStates();
        State newState = new State();
        newState.setName("");

        model.addAttribute("states", states);
        model.addAttribute("newState", newState);
        return "home";
    }
    @RequestMapping(value = "/createState", method= RequestMethod.POST)
    public String createState(HttpServletRequest request, @ModelAttribute(value="newState") State state) {

        if(ih.isValidName(state.getName())){
            return "error-400";
        }
        String referer = request.getHeader("Referer");
        stateService.create(state);
        //returns to prev. page
        return "redirect:"+ referer;
    }

    @RequestMapping(value = "/deleteState/{state}", method= RequestMethod.POST)
    public String deleteState(HttpServletRequest request, @PathVariable("state") State state) {
        stateService.deleteState(state);
        String referer = request.getHeader("Referer");
        //returns to prev. page
        return "redirect:"+ referer;
    }
}
