package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Currency;

public interface CurrencyRepo extends JpaRepository<Currency,Integer> {
    boolean existsByName(String name);
}
