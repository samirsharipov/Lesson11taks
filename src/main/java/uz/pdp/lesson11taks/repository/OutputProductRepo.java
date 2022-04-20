package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.OutputProduct;

public interface OutputProductRepo extends JpaRepository<OutputProduct,Integer> {
}
