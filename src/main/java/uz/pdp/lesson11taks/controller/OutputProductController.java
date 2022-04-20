package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.OutputProduct;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.OutputProductDto;
import uz.pdp.lesson11taks.repository.OutputProductRepo;
import uz.pdp.lesson11taks.service.OutputProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductRepo outputProductRepo;

    @Autowired
    OutputProductService service;

    @GetMapping
    public List<OutputProduct> getAll(){
        return outputProductRepo.findAll();
    }

    @GetMapping("/{id}")
    public OutputProduct getById(@PathVariable Integer id){
        Optional<OutputProduct> byId = outputProductRepo.findById(id);
        return byId.orElseGet(OutputProduct::new);
    }

    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto){
        return service.add(outputProductDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody OutputProductDto outputProductDto){
        return service.edit(id,outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        outputProductRepo.deleteById(id);
        return new Result("o'chirildi",true);
    }
}
