package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.Input;
import uz.pdp.lesson11taks.entity.InputProduct;
import uz.pdp.lesson11taks.entity.Product;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.InputProductDto;
import uz.pdp.lesson11taks.repository.InputProductRepo;
import uz.pdp.lesson11taks.repository.InputRepo;
import uz.pdp.lesson11taks.repository.ProductRepo;

import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepo inputProductRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    InputRepo inputRepo;

    public Result add(InputProductDto inputProductDto) {
        InputProduct inputProduct = new InputProduct();
        Optional<Product> byId = productRepo.findById(inputProductDto.getProductId());
        if (byId.isPresent()) {
            Product product = byId.get();
            inputProduct.setProduct(product);
            Optional<Input> inputRepoById = inputRepo.findById(inputProductDto.getInputId());
            if (inputRepoById.isPresent()) {
                Input input = inputRepoById.get();
                inputProduct.setInput(input);
                inputProduct.setAmount(inputProductDto.getAmount());
                inputProduct.setPrice(inputProductDto.getPrice());
                inputProduct.setExpireDate(inputProductDto.getExpireDate());
                inputProductRepo.save(inputProduct);
                return new Result("saqlandi!", true);
            }
            return new Result("bunday idlik input topilmadi!", false);
        }
        return new Result("bunday idlik product topilmadi!", false);
    }

    public Result edit(Integer id, InputProductDto inputProductDto) {
        Optional<InputProduct> byId = inputProductRepo.findById(id);
        if (byId.isPresent()) {
            InputProduct inputProduct = byId.get();
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(inputProductDto.getExpireDate());
            Optional<Product> productRepoById = productRepo.findById(inputProductDto.getProductId());
            if (productRepoById.isPresent()) {
                Product product = productRepoById.get();
                inputProduct.setProduct(product);
                Optional<Input> inputRepoById = inputRepo.findById(inputProductDto.getInputId());
                if (inputRepoById.isPresent()) {
                    Input input = inputRepoById.get();
                    inputProduct.setInput(input);
                    inputProductRepo.save(inputProduct);
                    return new Result("saqlandi!", true);
                }
                return new Result("bunday idlik input topilmadi!", false);
            }
            return new Result("bunday idlik product topilmadi!", false);
        }
        return new Result("bunday idlik inputProduct topilmadi!", false);
    }




}
