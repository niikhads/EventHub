package com.example.eventhub.service.impl;

import com.example.eventhub.dto.request.CategoryRequest;
import com.example.eventhub.dto.response.BaseResponse;
import com.example.eventhub.dto.response.CategoryResponse;
import com.example.eventhub.entity.Category;
import com.example.eventhub.exception.CategoryException;
import com.example.eventhub.mapper.CategoryMapper;
import com.example.eventhub.repository.CategoryRepository;
import com.example.eventhub.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryImplService implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryImplService.class);



    private CategoryRepository categoryRepository;


    private CategoryMapper categoryMapper;

    public BaseResponse<List<CategoryResponse>> getAllCategories() {
        try {
            logger.info("Fetching all categories from database.");
            List<Category> categories = categoryRepository.findAll();
            if (categories.isEmpty()) {
                throw new CategoryException("No categories found in the system.");
            }
            List<CategoryResponse> responses = categories.stream()
                    .map(categoryMapper::toResponse)
                    .collect(Collectors.toList());

            logger.info("Fetched {} categories successfully.", responses.size());
            return new BaseResponse<>(200, "Categories fetched successfully", responses);
        } catch (CategoryException e) {
            logger.error("Error occurred while fetching categories: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new CategoryException("Failed to fetch categories due to unexpected error.");
        }
    }

    public BaseResponse<CategoryResponse> getCategoryById(int id) {
        try {
            logger.info("Fetching category by ID: {}", id);
            Optional<Category> category = categoryRepository.findById(id);

            if (!category.isPresent()) {
                throw new CategoryException("Category with ID " + id + " not found.");
            }

            CategoryResponse response = categoryMapper.toResponse(category.get());
            logger.info("Category found with ID: {}", id);
            return new BaseResponse<>(200, "Category found", response);
        } catch (CategoryException e) {
            logger.error("Error occurred while fetching category: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new CategoryException("Failed to fetch category due to unexpected error.");
        }
    }

    public BaseResponse<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        try {
            logger.info("Creating new category: {}", categoryRequest);
            Category entity = categoryMapper.toEntity(categoryRequest);
            entity.setCreatedAt(LocalDateTime.now());
            entity.setUpdatedAt(LocalDateTime.now());
            Category savedEntity = categoryRepository.save(entity);
            CategoryResponse response = categoryMapper.toResponse(savedEntity);

            logger.info("Category created successfully with ID: {}", savedEntity.getId());
            return new BaseResponse<>(200, "Category successfully created", response);
        } catch (Exception e) {
            logger.error("Error occurred while creating category: {}", e.getMessage());
            throw new CategoryException("Failed to create category due to unexpected error.");
        }
    }

    public BaseResponse<CategoryResponse> updateCategory(int id, CategoryRequest categoryRequest) {
        try {
            logger.info("Updating category with ID: {}", id);
            Optional<Category> existingCategory = categoryRepository.findById(id);

            if (!existingCategory.isPresent()) {
                throw new CategoryException("Category with ID " + id + " not found.");
            }

            Category entityToUpdate = categoryMapper.toEntity(categoryRequest);
            entityToUpdate.setId(id);
            entityToUpdate.setUpdatedAt(LocalDateTime.now());
            Category updatedEntity = categoryRepository.save(entityToUpdate);
            CategoryResponse response = categoryMapper.toResponse(updatedEntity);

            logger.info("Category updated successfully with ID: {}", id);
            return new BaseResponse<>(200, "Category successfully updated", response);
        } catch (CategoryException e) {
            logger.error("Error occurred while updating category: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new CategoryException("Failed to update category due to unexpected error.");
        }
    }

    public BaseResponse<Void> deleteCategory(int id) {
        try {
            logger.info("Deleting category with ID: {}", id);
            Optional<Category> category = categoryRepository.findById(id);

            if (category.isEmpty()) {
                throw new CategoryException("Category with ID " + id + " not found.");
            }

            categoryRepository.deleteById(id);
            logger.info("Category with ID: {} successfully deleted", id);
            return new BaseResponse<>(200, "Category successfully deleted", null);
        } catch (CategoryException e) {
            logger.error("Error occurred while deleting category: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new CategoryException("Failed to delete category due to unexpected error.");
        }
    }
}
