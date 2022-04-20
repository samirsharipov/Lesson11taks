package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.entity.Warehouse;
import uz.pdp.lesson11taks.repository.WarehouseRepo;

import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepo warehouseRepo;

    public Result add(Warehouse warehouse) {
        boolean existsByName = warehouseRepo.existsByName(warehouse.getName());
        if (existsByName) {
            return new Result("bunday warehouse mavjud!",false);
        }
        warehouseRepo.save(warehouse);
        return new Result("saqlandi! ", true);
    }


    public Result edit(Integer id, Warehouse warehouse) {
        Optional<Warehouse> byId = warehouseRepo.findById(id);
        if (byId.isPresent()) {
            Warehouse warehouse1 = byId.get();
            boolean existsByName = warehouseRepo.existsByName(warehouse.getName());
            if (existsByName) {
                return new Result("bunday ismli warehouse mavjud",false);
            }
            warehouse1.setName(warehouse.getName());
            warehouseRepo.save(warehouse1);
            return new Result("saqlandi!",true);
        }
        return new Result("bunday idlik warehouse topilmadi!",false);
    }


}
