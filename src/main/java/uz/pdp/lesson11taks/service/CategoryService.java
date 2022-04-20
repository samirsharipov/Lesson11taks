package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.Category;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.payload.CategoryDto;
import uz.pdp.lesson11taks.repository.CategoryRepo;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public Result addCategory(CategoryDto dto) {
        boolean b = categoryRepo.existsByName(dto.getName());
        Category category = new Category();
        if (b) {
            return new Result("bunday category mavjud!", false);
        }
        category.setName(dto.getName());
        if (dto.getParentCategoryId() == null) {
            category.setParentCategory(null);
            categoryRepo.save(category);
            return new Result("category saqlandi!", true);
        }
        Optional<Category> byId = categoryRepo.findById(dto.getParentCategoryId());
        if (byId.isPresent()) {
            Category category1 = byId.get();
            category.setParentCategory(category1);
        }
        categoryRepo.save(category);
        return new Result("category saqlandi!", true);
    }

    public Result edit(Integer id, CategoryDto dto) {
        Optional<Category> byId = categoryRepo.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            category.setName(dto.getName());

            if (dto.getParentCategoryId() == null) {
                category.setParentCategory(null);
                categoryRepo.save(category);
                return new Result("category saqlandi!", true);
            }

            Integer parentCategoryId = dto.getParentCategoryId();
            Optional<Category> byId1 = categoryRepo.findById(parentCategoryId);
            if (byId1.isPresent()) {
                Category category1 = byId1.get();
                category.setParentCategory(category1);
            }
            categoryRepo.save(category);
            return new Result("category saqlandi!", true);
        }
        return new Result("bunday category mavjud! ", false);
    }

    public Result delete(Integer id) {
        boolean existsById = categoryRepo.existsById(id);
        if (existsById) {
            categoryRepo.deleteById(id);
            return new Result("Category o'chirildi! ", true);
        }
        return new Result("bunday Category topilmadi! ", false);
    }

}
