package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.*;
import uz.pdp.lesson11taks.payload.InputDto;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.CurrencyRepo;
import uz.pdp.lesson11taks.repository.InputRepo;
import uz.pdp.lesson11taks.repository.SupplierRepo;
import uz.pdp.lesson11taks.repository.WarehouseRepo;

import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepo inputRepo;

    @Autowired
    WarehouseRepo warehouseRepo;

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    CurrencyRepo currencyRepo;


    public Result add(InputDto inputDto) {
        Input input = new Input();
        input.setFactureNumber(inputDto.getFactureNumber());
        Optional<Warehouse> byId = warehouseRepo.findById(inputDto.getWarehouseId());
        if (byId.isPresent()) {
            Warehouse warehouse = byId.get();
            input.setWarehouse(warehouse);
            Optional<Supplier> byId1 = supplierRepo.findById(inputDto.getSupplierId());
            if (byId1.isPresent()) {
                Supplier supplier = byId1.get();
                input.setSupplier(supplier);
                Optional<Currency> byId2 = currencyRepo.findById(inputDto.getCurrencyId());
                if (byId2.isPresent()) {
                    Currency currency = byId2.get();
                    input.setCurrency(currency);
                    inputRepo.save(input);
                    return new Result("saqlandi!",true);
                }
                return new Result("bunday idlik currecy topilmadi!",false);
            }
            return new Result("bunday idlik supplier topilmadi!",false);
        }
        return new Result("bunday idlik warehouse topilmadi!",false);
    }


    public Result edit(Integer id, InputDto inputDto) {
        Optional<Input> byId = inputRepo.findById(id);
        if (byId.isPresent()) {
            Input input = byId.get();
            input.setFactureNumber(inputDto.getFactureNumber());
            Optional<Warehouse> warehouseRepoById = warehouseRepo.findById(inputDto.getWarehouseId());
            if (warehouseRepoById.isPresent()) {
                Warehouse warehouse = warehouseRepoById.get();
                input.setWarehouse(warehouse);
                Optional<Currency> currencyRepoById = currencyRepo.findById(inputDto.getCurrencyId());
                if (currencyRepoById.isPresent()) {
                    Currency currency = currencyRepoById.get();
                    input.setCurrency(currency);
                    Optional<Supplier> supplierRepoById = supplierRepo.findById(inputDto.getSupplierId());
                    if (supplierRepoById.isPresent()) {
                        Supplier supplier = supplierRepoById.get();
                        input.setSupplier(supplier);
                        inputRepo.save(input);
                        return new Result("o'zgartildi",true);
                    }
                    return new Result("bunday idlik supplier topilmadi!",false);
                }
                return new Result("bunday idlik currency topilmadi!",false);
            }
            return new Result("bunday idlik warehouse topilmadi!",false);
        }
        return new Result("bunday idlik input topilmadi!",false);
    }

    public Result delete(Integer id) {
        inputRepo.deleteById(id);
        return new Result("o'chirildi!",true);
    }
}
