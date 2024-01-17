package com.restapi.dto;

import com.restapi.model.Category;
import com.restapi.model.Property;
import com.restapi.response.CategoryResponse;
import com.restapi.response.PropertyResponse;
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


    public List<PropertyResponse> mapToCategoryProperties(Long categoryId, List<Property> propertyList) {
        List<PropertyResponse>propertyResponseList=new ArrayList<>();

        for(Property property:propertyList){
            System.out.println(property.getCategory().getId());
            if(categoryId.equals(property.getCategory().getId())) {
                System.out.println(property.getId());
                PropertyResponse propertyResponse = new PropertyResponse();
                propertyResponse.setCategoryName(property.getCategory().getTitle());
                propertyResponse.setCategoryId(property.getCategory().getId());
                propertyResponse.setId(property.getId());
                propertyResponse.setPropertyName(property.getPropertyName());
                propertyResponse.setPrice(property.getPrice());
                propertyResponse.setAddress(property.getAddress().getAddress());
                propertyResponse.setCity(property.getAddress().getCity());
                propertyResponse.setPhoto(property.getPhoto());
                propertyResponse.setZipcode(property.getAddress().getZipcode());
                propertyResponse.setApprove(property.isApproved());
                propertyResponse.setAgentName(property.getAgent().getAgentName());
                propertyResponse.setContactNumber(property.getAgent().getContactNumber());
                propertyResponseList.add(propertyResponse);
            }
        }
        return propertyResponseList;
    }
}
