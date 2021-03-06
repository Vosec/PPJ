package cz.tul.controllers.web;

import cz.tul.helper.InputHelper;
import cz.tul.model.City;
import cz.tul.model.State;
import cz.tul.service.CityService;
import cz.tul.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StatesControllerWeb {
    private CityService cityService;
    private StateService stateService;
    private InputHelper ih = new InputHelper();

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value="/state/{state}", method = RequestMethod.GET)
    public String showCities(@PathVariable("state") String state, Model model) {
        List<City> cities = cityService.getCitiesByStateName(state);
        City city = new City();
        model.addAttribute("state", state);
        model.addAttribute("cities", cities);
        model.addAttribute("city", city);
        return "state";
    }
    @RequestMapping(value = "/{state}/createCity", method= RequestMethod.POST)
    public String createCity(HttpServletRequest request, @ModelAttribute(value="newCity") City city, @PathVariable("state") String state) {
        State s = stateService.get(state);
        if(ih.isValidName(city.getName()) | ih.isValidCityId(city.getCityId())){
            return "error-400";
        }
        City c = new City(city.getName(), s, city.getCityId());
        cityService.create(c);
        String referer = request.getHeader("Referer");
        //returns to prev. page
        return "redirect:"+ referer;
    }

    @RequestMapping(value = "/deleteCity/{cityId}", method= RequestMethod.POST)
    public String deleteCity(HttpServletRequest request, @PathVariable("cityId") int cityId) {
        City c = cityService.getCityByCityId(cityId);
        cityService.delete(c);
        String referer = request.getHeader("Referer");
        //returns to prev. page
        return "redirect:"+ referer;
    }
}
