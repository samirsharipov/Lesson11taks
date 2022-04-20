package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.entity.Supplier;
import uz.pdp.lesson11taks.repository.SupplierRepo;
import uz.pdp.lesson11taks.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    SupplierService service;

    @GetMapping
    public List<Supplier> getAll(){
        return supplierRepo.findAll();
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody Supplier supplier){
        return service.add(supplier);
    }
}
