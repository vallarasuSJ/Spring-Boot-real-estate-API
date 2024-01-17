package com.restapi.service;

import com.restapi.dto.CategoryDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Property;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.PropertyRepository;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CategoryDto categoryDto;

    public void createCategory(CategoryRequest categoryRequest) {
        Category category=new Category();
        category.setId(categoryRequest.getId());
        category.setTitle(categoryRequest.getTitle());
        categoryRepository.save(category);
    }

    public List<CategoryResponse> findAll() {
        List<Category>categoryList=categoryRepository.findAll();
        List<CategoryResponse> categoryResponses=categoryDto.mapToCategory(categoryList);
        return categoryResponses;
    }

    public List<PropertyResponse> getCategoryProperties(Long categoryId) {
        List<Property> propertyList = propertyRepository.findAllAvailableProperties();
        List<PropertyResponse> categoryProperties=categoryDto.mapToCategoryProperties(categoryId,propertyList);
        return categoryProperties;
    }
}
