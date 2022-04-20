package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.entity.Client;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.ClientRepo;
import uz.pdp.lesson11taks.service.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientRepo clientRepo;

    @Autowired
    ClientService clientService;

    @GetMapping
    public List<Client> getAll(){
        return clientRepo.findAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id){
        Optional<Client> byId = clientRepo.findById(id);
        return byId.orElseGet(Client::new);
    }

    @PostMapping
    public Result add(@RequestBody Client client){
        return clientService.add(client);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Client client){
        return clientService.edit(id,client);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return clientService.delete(id);
    }


}
