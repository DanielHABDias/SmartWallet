package com.danieldias.smart_wallet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/category")
public class CategoryController {

    @GetMapping()
    public String getCategories() {
        return "TESTE";
    }

    @GetMapping("/{categoryId}")
    public String getCategory(@PathVariable int categoryId){
        return "TESTE" + categoryId;
    }
}
