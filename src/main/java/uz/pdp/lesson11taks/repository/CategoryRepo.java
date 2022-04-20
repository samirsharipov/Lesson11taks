package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
}
