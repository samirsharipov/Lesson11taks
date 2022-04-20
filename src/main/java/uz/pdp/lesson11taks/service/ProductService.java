package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.*;
import uz.pdp.lesson11taks.payload.ProductDto;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.AttachmentRepo;
import uz.pdp.lesson11taks.repository.CategoryRepo;
import uz.pdp.lesson11taks.repository.MeasurementRepo;
import uz.pdp.lesson11taks.repository.ProductRepo;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    MeasurementRepo measurementRepo;

    @Autowired
    AttachmentRepo attachmentRepo;

    public Product getById(Integer id) {
        Optional<Product> byId = productRepo.findById(id);
        return byId.orElseGet(Product::new);
    }

    public Result add(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        Optional<Category> byId = categoryRepo.findById(productDto.getCategoryId());
        if (byId.isPresent()) {
            Category category = byId.get();
            product.setCategory(category);
            Optional<Attachment> attachmentRepoById = attachmentRepo.findById(productDto.getAttachmentId());
            if (attachmentRepoById.isPresent()) {
                Attachment attachment = attachmentRepoById.get();
                product.setAttachment(attachment);
                Optional<Measurement> measurementRepoById = measurementRepo.findById(productDto.getMeasurementId());
                if (measurementRepoById.isPresent()) {
                    Measurement measurement = measurementRepoById.get();
                    product.setMeasurement(measurement);
                    productRepo.save(product);
                    return new Result("product saqlandi!", true);
                }
                return new Result("bunday idlik measurement topilmadi!", false);
            }
            return new Result("bunday idlik attachment topilmadi", false);
        }
        return new Result("bunday idlik category topilmadi!", false);
    }

    public Result edit(Integer id, ProductDto productDto) {
        Optional<Product> byId = productRepo.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setName(productDto.getName());
            if (productDto.getCategoryId() != null) {
                Optional<Category> categoryRepoById = categoryRepo.findById(productDto.getCategoryId());
                if (categoryRepoById.isPresent()) {
                    Category category = categoryRepoById.get();
                    product.setCategory(category);
                }
            }
            if (productDto.getAttachmentId() != null) {
                Optional<Attachment> attachmentRepoById = attachmentRepo.findById(productDto.getAttachmentId());
                if (attachmentRepoById.isPresent()) {
                    Attachment attachment = attachmentRepoById.get();
                    product.setAttachment(attachment);
                }
            }
            if (productDto.getMeasurementId() != null) {
                Optional<Measurement> measurementRepoById = measurementRepo.findById(productDto.getMeasurementId());
                if (measurementRepoById.isPresent()) {
                    Measurement measurement = measurementRepoById.get();
                    product.setMeasurement(measurement);
                }
            }

            Product save = productRepo.save(product);
            return new Result("o'zgartirildi!",true);
        }
        return new Result("bunday idlik product topilmadi!",false);
    }


    public Result delete(Integer id) {
        productRepo.deleteById(id);
        return new Result("o'chirildi",true);
    }
}
