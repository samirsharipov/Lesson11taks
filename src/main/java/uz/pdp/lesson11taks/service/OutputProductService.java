package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.Output;
import uz.pdp.lesson11taks.entity.OutputProduct;
import uz.pdp.lesson11taks.entity.Product;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.OutputProductDto;
import uz.pdp.lesson11taks.repository.OutputProductRepo;
import uz.pdp.lesson11taks.repository.OutputRepo;
import uz.pdp.lesson11taks.repository.ProductRepo;

import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepo outputProductRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OutputRepo outputRepo;

    public Result add(OutputProductDto outputProductDto) {
        OutputProduct outputProduct = new OutputProduct();
        Optional<Product> byId = productRepo.findById(outputProductDto.getProductId());
        if (byId.isPresent()) {
            Product product = byId.get();
            outputProduct.setProduct(product);
            Optional<Output> outputRepoById = outputRepo.findById(outputProductDto.getOutputId());
            if (outputRepoById.isPresent()) {
                Output output = outputRepoById.get();
                outputProduct.setOutput(output);
                outputProduct.setAmount(outputProductDto.getAmount());
                outputProduct.setPrice(outputProductDto.getPrice());
                outputProductRepo.save(outputProduct);
                return new Result("saqlandi!", true);
            }
            return new Result("bunday idlik output topilmadi!", false);
        }
        return new Result("bunday idlik product topilmadi!", false);
    }

    public Result edit(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> byId = outputProductRepo.findById(id);
        if (byId.isPresent()) {
            OutputProduct outputProduct = byId.get();
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());
            Optional<Product> productRepoById = productRepo.findById(outputProductDto.getProductId());
            if (productRepoById.isPresent()) {
                Product product = productRepoById.get();
                outputProduct.setProduct(product);
                Optional<Output> outputRepoById = outputRepo.findById(outputProductDto.getOutputId());
                if (outputRepoById.isPresent()) {
                    Output output = outputRepoById.get();
                    outputProduct.setOutput(output);
                    outputProductRepo.save(outputProduct);
                    return new Result("saqlandi!", true);
                }
                return new Result("bunday idlik output topilmadi!", false);
            }
            return new Result("bunday idlik product topilmadi!", false);
        }
        return new Result("bunday idlik outputProduct topilmadi!", false);
    }




}
