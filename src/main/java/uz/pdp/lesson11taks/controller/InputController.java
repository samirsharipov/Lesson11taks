package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.Input;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.InputDto;
import uz.pdp.lesson11taks.repository.InputRepo;
import uz.pdp.lesson11taks.service.InputService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputRepo inputRepo;

    @Autowired
    InputService service;


    @GetMapping
    public List<Input> getAll(){
        return inputRepo.findAll();
    }

    @GetMapping("/{id}")
    public Input getById(@PathVariable Integer id){
        Optional<Input> byId = inputRepo.findById(id);
        return byId.orElseGet(Input::new);
    }

    @PostMapping
    public Result add(@RequestBody InputDto inputDto){
        return service.add(inputDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return service.edit(id,inputDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
