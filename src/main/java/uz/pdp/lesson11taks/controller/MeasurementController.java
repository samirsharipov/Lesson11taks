package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.Measurement;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.MeasurementRepo;
import uz.pdp.lesson11taks.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementRepo measurementRepo;

    @Autowired
    MeasurementService measurementService;


    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement){
        return measurementService.addMeasurement(measurement);
    }

    @GetMapping
    public List<Measurement> get(){
        return measurementRepo.findAll();
    }

    @GetMapping("/getbyid/{id}")
    public Measurement getById(@PathVariable Integer id){
        return measurementRepo.getById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.editMeasurement(id,measurement);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return measurementService.deleteMeasurement(id);
    }
 }
