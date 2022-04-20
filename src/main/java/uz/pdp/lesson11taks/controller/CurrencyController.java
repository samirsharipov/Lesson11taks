package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.Currency;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.CurrencyRepo;
import uz.pdp.lesson11taks.service.CurrencyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepo currencyRepo;

    @Autowired
    CurrencyService service;


    @GetMapping
    public List<Currency> getAll(){
       return currencyRepo.findAll();
    }

    @PostMapping
    public Result add(@RequestBody Currency currency){
        return service.addCurrency(currency);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Currency currency){
        return service.edit(id,currency);
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Integer id){
        Optional<Currency> byId = currencyRepo.findById(id);
        return byId.orElseGet(Currency::new);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        currencyRepo.deleteById(id);
        return new Result("o'chirildi! ", true);
    }
}
