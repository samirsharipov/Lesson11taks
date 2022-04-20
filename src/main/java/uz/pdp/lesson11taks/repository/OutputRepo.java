package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Output;

public interface OutputRepo extends JpaRepository<Output,Integer> {
}
