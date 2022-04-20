package uz.pdp.lesson11taks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.entity.User;
import uz.pdp.lesson11taks.payload.UserDto;
import uz.pdp.lesson11taks.repository.UserRepo;
import uz.pdp.lesson11taks.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        Optional<User> byId = userRepo.findById(id);
        return byId.orElseGet(User::new);
    }

    @PostMapping
    public Result add(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.edit(id,userDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        userRepo.deleteById(id);
        return new Result("o'chirildi!",true);
    }
}
