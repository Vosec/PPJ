package cz.tul.controllers;

import cz.tul.api.RestApi;
import cz.tul.model.Measurement;
import cz.tul.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeasurementController {

    private MeasurementService measurementService;

    @Autowired
    public void setMeasurementService(MeasurementService measurementService){
        this.measurementService = measurementService;
    }

    @RequestMapping(value = RestApi.MEASUREMENTS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Measurement>> getMeasurements() {
        List<Measurement> measurements = measurementService.findAll();

        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }
}
