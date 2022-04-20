package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.InputProduct;

public interface InputProductRepo extends JpaRepository<InputProduct,Integer> {
}
