package com.restapi.dto;

import com.restapi.model.Category;
import com.restapi.model.Property;
import com.restapi.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDto {

    public List<CategoryResponse> mapToCategory(List<Category> categoryList) {
        List<CategoryResponse> categoryResponses=new ArrayList<>();
        for(Category category:categoryList) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(category.getId());
            categoryResponse.setCategoryName(category.getTitle());
            categoryResponses.add(categoryResponse);
        }
        return  categoryResponses;
    }


    public List<CategoryResponse> mapToCategoryProperties(Long categoryId, List<Property> propertyList) {
        List<CategoryResponse>categoryResponses=new ArrayList<>();

        for(Property property:propertyList){
            System.out.println(property.getCategory().getId());
            if(categoryId.equals(property.getCategory().getId())) {
                System.out.println(property.getId());
                CategoryResponse categoryResponse = new CategoryResponse();
                categoryResponse.setCategoryName(property.getCategory().getTitle());
                categoryResponse.setCategoryId(property.getCategory().getId());
                categoryResponse.setPropertyId(property.getId());
                categoryResponse.setPropertyName(property.getPropertyName());
                categoryResponse.setPrice(property.getPrice());
                categoryResponse.setAddress(property.getAddress().getAddress());
                categoryResponse.setCity(property.getAddress().getCity());
                categoryResponse.setPhoto(property.getPhoto());
                categoryResponse.setZipcode(property.getAddress().getZipcode());
                categoryResponse.setApprove(property.isApproved());
                categoryResponse.setAgentName(property.getAgent().getAgentName());
                categoryResponse.setContactNumber(property.getAgent().getContactNumber());
                categoryResponses.add(categoryResponse);
            }
        }
        return categoryResponses;
    }
}
