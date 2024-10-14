package com.example.eventhub.service;

import com.example.eventhub.dto.request.CategoryRequest;
import com.example.eventhub.dto.response.BaseResponse;
import com.example.eventhub.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    public BaseResponse<List<CategoryResponse>> getAllCategories() ;
    public BaseResponse<CategoryResponse> getCategoryById(int id) ;
    public BaseResponse<CategoryResponse> createCategory(CategoryRequest categoryRequest) ;
    public BaseResponse<CategoryResponse> updateCategory(int id, CategoryRequest categoryRequest) ;
    public BaseResponse<Void> deleteCategory(int id) ;

    }
