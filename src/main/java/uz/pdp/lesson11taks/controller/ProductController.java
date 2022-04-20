package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.Product;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.ProductDto;
import uz.pdp.lesson11taks.repository.ProductRepo;
import uz.pdp.lesson11taks.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product>getAll(){
        return productRepo.findAll();
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Integer id){
        return productService.getById(id);
    }

    @GetMapping("/categoryId/{id}")
    public List<Product> getByCategoryId(@PathVariable Integer id){
        return productRepo.findByCategoryId(id);
    }

    @PostMapping
    public Result add(@RequestBody ProductDto productDto){
        return productService.add(productDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.edit(id,productDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return productService.delete(id);
    }
}
