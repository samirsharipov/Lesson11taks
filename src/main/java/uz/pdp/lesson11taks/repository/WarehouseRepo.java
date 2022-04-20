package uz.pdp.lesson11taks.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Warehouse;

import java.util.Set;

public interface WarehouseRepo extends JpaRepository<Warehouse,Integer> {
    boolean existsByName(String name);
    Set<Warehouse>findAllByIds(Set<Integer> id);
}
