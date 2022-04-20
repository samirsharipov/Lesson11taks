package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Product;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Product>findByCategoryId(Integer category_id);
}
