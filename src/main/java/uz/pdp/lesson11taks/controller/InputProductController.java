package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.InputProduct;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.InputProductDto;
import uz.pdp.lesson11taks.repository.InputProductRepo;
import uz.pdp.lesson11taks.service.InputProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductRepo inputProductRepo;

    @Autowired
    InputProductService service;

    @GetMapping
    public List<InputProduct> getAll(){
        return inputProductRepo.findAll();
    }

    @GetMapping("/{id}")
    public InputProduct getById(@PathVariable Integer id){
        Optional<InputProduct> byId = inputProductRepo.findById(id);
        return byId.orElseGet(InputProduct::new);
    }

    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto){
        return service.add(inputProductDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody InputProductDto inputProductDto){
        return service.edit(id,inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        inputProductRepo.deleteById(id);
        return new Result("o'chirildi",true);
    }
}
