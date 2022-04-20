package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.Client;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.ClientRepo;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepo clientRepo;

    public Result add(Client client) {
        clientRepo.save(client);
        return new Result("saqlandi!", true);
    }


    public Result edit(Integer id, Client client) {
        Optional<Client> byId = clientRepo.findById(id);
        if (byId.isPresent()) {
            Client client1 = byId.get();
            client1.setName(client.getName());
            client1.setPhoneNumber(client.getPhoneNumber());
            clientRepo.save(client1);
            return new Result("o'zgartirildi",true);
        }
        return new Result("bunday idlik client mavjud emas!",false);
    }

    public Result delete(Integer id) {
        clientRepo.deleteById(id);
        return new Result("o'chirildi!",true);
    }
}
