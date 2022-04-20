package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.*;
import uz.pdp.lesson11taks.payload.OutputDto;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.CurrencyRepo;
import uz.pdp.lesson11taks.repository.OutputRepo;
import uz.pdp.lesson11taks.repository.ClientRepo;
import uz.pdp.lesson11taks.repository.WarehouseRepo;

import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepo outputRepo;

    @Autowired
    WarehouseRepo warehouseRepo;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    CurrencyRepo currencyRepo;


    public Result add(OutputDto outputDto) {
        Output output = new Output();
        output.setFactureNumber(outputDto.getFactureNumber());
        Optional<Warehouse> byId = warehouseRepo.findById(outputDto.getWarehouseId());
        if (byId.isPresent()) {
            Warehouse warehouse = byId.get();
            output.setWarehouse(warehouse);
            Optional<Client> byId1 = clientRepo.findById(outputDto.getClientId());
            if (byId1.isPresent()) {
                Client client = byId1.get();
                output.setClient(client);
                Optional<Currency> byId2 = currencyRepo.findById(outputDto.getCurrencyId());
                if (byId2.isPresent()) {
                    Currency currency = byId2.get();
                    output.setCurrency(currency);
                    outputRepo.save(output);
                    return new Result("saqlandi!",true);
                }
                return new Result("bunday idlik currecy topilmadi!",false);
            }
            return new Result("bunday idlik client topilmadi!",false);
        }
        return new Result("bunday idlik warehouse topilmadi!",false);
    }


    public Result edit(Integer id, OutputDto outputDto) {
        Optional<Output> byId = outputRepo.findById(id);
        if (byId.isPresent()) {
            Output output = byId.get();
            output.setFactureNumber(outputDto.getFactureNumber());
            Optional<Warehouse> warehouseRepoById = warehouseRepo.findById(outputDto.getWarehouseId());
            if (warehouseRepoById.isPresent()) {
                Warehouse warehouse = warehouseRepoById.get();
                output.setWarehouse(warehouse);
                Optional<Currency> currencyRepoById = currencyRepo.findById(outputDto.getCurrencyId());
                if (currencyRepoById.isPresent()) {
                    Currency currency = currencyRepoById.get();
                    output.setCurrency(currency);
                    Optional<Client> clientRepoById = clientRepo.findById(outputDto.getClientId());
                    if (clientRepoById.isPresent()) {
                        Client client = clientRepoById.get();
                        output.setClient(client);
                        outputRepo.save(output);
                        return new Result("o'zgartildi",true);
                    }
                    return new Result("bunday idlik client topilmadi!",false);
                }
                return new Result("bunday idlik currency topilmadi!",false);
            }
            return new Result("bunday idlik warehouse topilmadi!",false);
        }
        return new Result("bunday idlik output topilmadi!",false);
    }

    public Result delete(Integer id) {
        outputRepo.deleteById(id);
        return new Result("o'chirildi!",true);
    }
}
