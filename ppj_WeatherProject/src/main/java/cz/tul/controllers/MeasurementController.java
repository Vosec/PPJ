package cz.tul.controllers;

import cz.tul.api.RestApi;
import cz.tul.model.Measurement;
import cz.tul.service.MeasurementService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = RestApi.MEASUREMENTS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Measurement> createMeasurement(@RequestBody Measurement measurement) {
        measurementService.addMeasurement(measurement);
        return new ResponseEntity<>(measurement, HttpStatus.OK);
    }

    @RequestMapping(value = RestApi.MEASUREMENT_PATH, method = RequestMethod.GET)
    public ResponseEntity<Measurement> getMeasurement(@PathVariable("id") ObjectId id) {
        Measurement measurement = measurementService.getMeasurement(id);
        if (measurement != null) {
            return new ResponseEntity<>(measurement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = RestApi.MEASUREMENT_PATH, method = RequestMethod.POST)
    public ResponseEntity<Measurement> updateMeasurement(@RequestBody Measurement measurement) {
        Measurement requestedMeasurement = measurementService.getMeasurement(measurement.getIdObj());
        if (requestedMeasurement != null) {
            measurementService.deleteMeasurement(requestedMeasurement);
            Measurement newMeasurement = measurementService.save(measurement);
            return new ResponseEntity<>(newMeasurement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = RestApi.MEASUREMENT_PATH, method = RequestMethod.DELETE)
    public ResponseEntity deleteMeasurement(@PathVariable("id") ObjectId id) {
        Measurement measurement = measurementService.getMeasurement(id);
        if (measurement != null) {
            measurementService.deleteMeasurement(measurement);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
