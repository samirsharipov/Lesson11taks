package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Measurement;

public interface MeasurementRepo extends JpaRepository<Measurement,Integer> {
    boolean existsByName(String name);
}
