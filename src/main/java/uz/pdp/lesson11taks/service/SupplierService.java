package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.entity.Supplier;
import uz.pdp.lesson11taks.repository.SupplierRepo;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepo supplierRepo;

    public Supplier getById(Integer id) {
        Optional<Supplier> byId = supplierRepo.findById(id);
        return byId.orElseGet(Supplier::new);
    }

    public Result add(Supplier supplier) {
        boolean existsByPhoneNumber = supplierRepo.existsByPhoneNumber(supplier.getPhoneNumber());

        if (existsByPhoneNumber) {
            return new Result("bunday raqamlik supplier mavjud!",false);
        }

        supplierRepo.save(supplier);
        return new Result("Saqlandi!",true);
    }
}
