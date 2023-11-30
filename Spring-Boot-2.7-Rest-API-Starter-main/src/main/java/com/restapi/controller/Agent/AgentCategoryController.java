package com.restapi.controller.Agent;

import com.restapi.model.Category;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.PropertyResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/agent/category")
public class AgentCategoryController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<APIResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        List<CategoryResponse> categoryResponses=categoryService.createCategory(categoryRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse>getAllCategories(){
        List<CategoryResponse> categoryResponses=categoryService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponses);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<APIResponse> getCategoryProperties(@PathVariable Long categoryId){
        List<CategoryResponse> categoryResponses=categoryService.getCategoryProperties(categoryId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponses);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


}
