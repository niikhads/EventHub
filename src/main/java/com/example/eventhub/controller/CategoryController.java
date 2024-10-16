package com.example.eventhub.controller;

import com.example.eventhub.dto.request.CategoryRequest;
import com.example.eventhub.dto.response.BaseResponse;
import com.example.eventhub.dto.response.CategoryResponse;
import com.example.eventhub.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryResponse>>> getAllCategories() {
        BaseResponse<List<CategoryResponse>> categoryResponse = categoryService.getAllCategories();
        return ResponseEntity.status(categoryResponse.getCode()).body(categoryResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryResponse>> getCategoryById(@PathVariable Long id) {
        BaseResponse<CategoryResponse> categoryResponse = categoryService.getCategoryById(id);
        return ResponseEntity.status(categoryResponse.getCode()).body(categoryResponse);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CategoryResponse>> createCategory(@RequestBody CategoryRequest categoryRequest) {
        BaseResponse<CategoryResponse> categoryResponse = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(categoryResponse.getCode()).body(categoryResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryResponse>> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        BaseResponse<CategoryResponse> categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.status(categoryResponse.getCode()).body(categoryResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCategory(@PathVariable Long id) {
        BaseResponse<Void> categoryResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(categoryResponse.getCode()).body(categoryResponse);
    }
}
