package cz.tul.controllers.web;

import cz.tul.model.City;
import cz.tul.model.Measurement;
import cz.tul.service.CityService;
import cz.tul.model.MeasurementAvg;
import cz.tul.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CityControllerWeb {
    private CityService cityService;
    private MeasurementService measurementService;

    @Autowired
    public void setMeasurementService(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value="/state/{state}/{cityId}/two_weeks", method = RequestMethod.GET)
    public String showCitiesTwoWeeks(@PathVariable("state") String state, @PathVariable("cityId") int cityId, Model model) {
        City city = cityService.getCityByCityId(cityId);
        MeasurementAvg meas  = measurementService.MeasurementAvgTwoWeeks(cityId);
        String title = "Two weeks average measurement";
        model.addAttribute("title", title);
        model.addAttribute("city", city.getName());
        model.addAttribute("meas", meas);

        return "city";
    }

    @RequestMapping(value="/state/{state}/{cityId}/actual", method = RequestMethod.GET)
    public String showCitiesActual(@PathVariable("state") String state, @PathVariable("cityId") int cityId, Model model) {
        City city = cityService.getCityByCityId(cityId);
        Measurement meas  = measurementService.getActualMeasurement(cityId);
        String title = "Actual measurement";
        model.addAttribute("title", title);
        model.addAttribute("city", city.getName());
        model.addAttribute("meas", meas);

        return "city";
    }
    @RequestMapping(value="/state/{state}/{cityId}/oneday", method = RequestMethod.GET)
    public String showCitiesOneday(@PathVariable("state") String state, @PathVariable("cityId") int cityId, Model model) {
        City city = cityService.getCityByCityId(cityId);
        MeasurementAvg meas  = measurementService.MeasurementAvgOneDay(cityId);
        String title = "One day average measurement";
        model.addAttribute("title", title);
        model.addAttribute("city", city.getName());
        model.addAttribute("meas", meas);

        return "city";
    }
    @RequestMapping(value="/state/{state}/{cityId}/week", method = RequestMethod.GET)
    public String showCitiesOneweek(@PathVariable("state") String state, @PathVariable("cityId") int cityId, Model model) {
        City city = cityService.getCityByCityId(cityId);
        MeasurementAvg meas  = measurementService.MeasurementAvgOneWeek(cityId);
        String title = "One week average measurement";
        model.addAttribute("title", title);
        model.addAttribute("city", city.getName());
        model.addAttribute("meas", meas);

        return "city";
    }
}
