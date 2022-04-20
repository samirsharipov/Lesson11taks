package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.entity.User;
import uz.pdp.lesson11taks.entity.Warehouse;
import uz.pdp.lesson11taks.payload.UserDto;
import uz.pdp.lesson11taks.repository.UserRepo;
import uz.pdp.lesson11taks.repository.WarehouseRepo;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    WarehouseRepo warehouseRepo;

    public Result add(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPassword(userDto.getPassword());
        boolean existsByPhoneNumber = userRepo.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("bunday phone number mavjud!",false);
        }
        user.setPhoneNumber(userDto.getPhoneNumber());
        Set<Warehouse> allById = warehouseRepo.findAllByIds(userDto.getWarehouseIds());
        user.setWarehouses(allById);
        userRepo.save(user);
        return new Result("saqlandi!",true);
    }

    public Result edit(Integer id, UserDto userDto) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setPassword(userDto.getPassword());
            boolean existsByPhoneNumber = userRepo.existsByPhoneNumber(userDto.getPhoneNumber());
            if (existsByPhoneNumber) {
                return new Result("bunday phone number mavjud!",false);
            }
            user.setPhoneNumber(userDto.getPhoneNumber());
            Set<Warehouse> allById = warehouseRepo.findAllByIds(userDto.getWarehouseIds());
            user.setWarehouses(allById);
            userRepo.save(user);
            return new Result("saqlandi!",true);
        }
        return new Result("bunday idlik user topilmadi!",false);
    }
}
