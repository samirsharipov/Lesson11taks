package uz.pdp.lesson11taks.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Client;

public interface ClientRepo extends JpaRepository<Client,Integer> {
}
