package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Input;

public interface InputRepo extends JpaRepository<Input,Integer> {
}
