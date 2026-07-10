package com.danieldias.smart_wallet.service;

import com.danieldias.smart_wallet.domain.entity.CategoryEntity;
import com.danieldias.smart_wallet.dto.CreateCategoryDTO;
import com.danieldias.smart_wallet.exception.custom.CategoryNotFoundException;
import com.danieldias.smart_wallet.exception.custom.DatabaseCreateException;
import com.danieldias.smart_wallet.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public List<CategoryEntity> getCategories() {
        List<CategoryEntity> listCategories = this.repository.findAll();
        if(listCategories.isEmpty()){
            throw new CategoryNotFoundException("No categories found.");
        }
        return  listCategories;
    }

    public CategoryEntity getCategory(Long id){
        return this.repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found."));
    }

    public CategoryEntity createCategory(CreateCategoryDTO params) {
        try{
            CategoryEntity category = CategoryEntity.builder()
                    .name(params.name().toUpperCase().replace(" ", "_").trim())
                    .color(params.color().toUpperCase().trim())
                    .unicodeIcon(params.unicodeIcon().toUpperCase().trim())
                    .transactionType(params.transactionType())
                    .description(params.description().trim())
                    .build();

            return this.repository.save(category);
        } catch (Exception e) {
            throw new DatabaseCreateException("Error creating category in database");
        }
    }

    public List<CategoryEntity> createCategories(List<CreateCategoryDTO> params) {
        try{
            List<CategoryEntity> entities = params.stream()
                    .map(dto -> CategoryEntity.builder()
                            .name(dto.name().toUpperCase().replace(" ", "_").trim())
                            .color(dto.color().toUpperCase().trim())
                            .unicodeIcon(dto.unicodeIcon().toUpperCase().trim())
                            .transactionType(dto.transactionType())
                            .description(dto.description().trim())
                            .build())
                    .toList();
            return this.repository.saveAll(entities);
        } catch (Exception e) {
            throw new DatabaseCreateException("Error creating category in database");
        }
    }
}
