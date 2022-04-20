package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.Measurement;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.MeasurementRepo;

import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepo measurementRepo;

    public  Result addMeasurement(Measurement measurement) {
        boolean b = measurementRepo.existsByName(measurement.getName());
        if (b) {
            return new Result("bunday measurement bor!", false);
        }
        measurementRepo.save(measurement);
        return new Result("measurement saqlandi! ", true);
    }

    public Result editMeasurement(Integer id, Measurement measurement){
        Optional<Measurement> byId = measurementRepo.findById(id);
        if (!byId.isPresent()) {
            return new Result("bunday measurement topilmadi", false);
        }
        measurementRepo.save(measurement);
        return new Result("mesurement saqlandi!", true);
    }

    public Result deleteMeasurement(Integer id) {
        boolean existsById = measurementRepo.existsById(id);
        if (existsById) {
            measurementRepo.deleteById(id);
            return new Result("Measurement o'chirildi! ", true);
        }
        return new Result("bunday measurement topilmadi! ", false);
    }
}
