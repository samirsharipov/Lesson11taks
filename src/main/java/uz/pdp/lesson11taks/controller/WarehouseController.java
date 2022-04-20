package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.entity.Warehouse;
import uz.pdp.lesson11taks.repository.WarehouseRepo;
import uz.pdp.lesson11taks.service.WarehouseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseRepo warehouseRepo;

    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getAll(){
        return warehouseRepo.findAll();
    }

    @PostMapping
    public Result add(@RequestBody Warehouse warehouse){
        return warehouseService.add(warehouse);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Warehouse warehouse){
        return warehouseService.edit(id,warehouse);
    }

    @GetMapping("/{id}")
    public Warehouse getById(@PathVariable Integer id){
        Optional<Warehouse> byId = warehouseRepo.findById(id);
        return byId.orElseGet(Warehouse::new);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        warehouseRepo.deleteById(id);
        return new Result("o'chirildi!",true);
    }
}
