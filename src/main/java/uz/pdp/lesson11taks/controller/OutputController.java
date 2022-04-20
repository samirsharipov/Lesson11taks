package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.Output;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.OutputDto;
import uz.pdp.lesson11taks.repository.OutputRepo;
import uz.pdp.lesson11taks.service.OutputService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputRepo outputRepo;

    @Autowired
    OutputService service;


    @GetMapping
    public List<Output> getAll(){
        return outputRepo.findAll();
    }

    @GetMapping("/{id}")
    public Output getById(@PathVariable Integer id){
        Optional<Output> byId = outputRepo.findById(id);
        return byId.orElseGet(Output::new);
    }

    @PostMapping
    public Result add(@RequestBody OutputDto outputDto){
        return service.add(outputDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        return service.edit(id,outputDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
