package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.Category;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.CategoryDto;
import uz.pdp.lesson11taks.repository.CategoryRepo;
import uz.pdp.lesson11taks.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    CategoryService service;

    @PostMapping
    public Result create(@RequestBody CategoryDto dto){
        return service.addCategory(dto);
    }

    @GetMapping
    public List<Category> get(){
        return categoryRepo.findAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Integer id){
        Optional<Category> byId = categoryRepo.findById(id);
        return byId.get();
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody CategoryDto dto){
        return service.edit(id,dto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
