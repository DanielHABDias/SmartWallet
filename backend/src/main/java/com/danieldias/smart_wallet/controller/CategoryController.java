package com.danieldias.smart_wallet.controller;

import com.danieldias.smart_wallet.domain.entity.CategoryEntity;
import com.danieldias.smart_wallet.dto.ApiResponseDTO;
import com.danieldias.smart_wallet.dto.CreateCategoryDTO;
import com.danieldias.smart_wallet.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<List<CategoryEntity>>> getCategories() {
        List<CategoryEntity> result = this.categoryService.getCategories();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDTO<List<CategoryEntity>>(true, result.size() + " categories found!", result));
    }

    @GetMapping("/{categoryId}")
    public CategoryEntity getCategory(@PathVariable Long categoryId){
        return this.categoryService.getCategory(categoryId);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<CategoryEntity>> getCategory(@RequestBody @Valid CreateCategoryDTO params){
        CategoryEntity result = this.categoryService.createCategory(params);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseDTO<CategoryEntity>(true, "Category created!", result));
    }
}
