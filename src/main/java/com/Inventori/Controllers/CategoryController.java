package com.Inventori.Controllers;

import com.Inventori.Models.Category;
import com.Inventori.Repository.CategoryRepository;
import com.Inventori.Serivces.CategoriInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoriInterface categoriInterface;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategories() {
        try {
            List<Category> data = categoryRepository.findAll();
            Map<String, Object> categories = new HashMap<String, Object>();

            categories.put("status", "success");
            categories.put("response", data);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<?> insertCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
        try {
            Map<String, Object> categories = new HashMap<String, Object>();
            if (bindingResult.hasErrors()) {
                StringBuilder errors = new StringBuilder();
                bindingResult.getAllErrors().forEach(error ->
                        errors.append(error.getDefaultMessage()).append(" ")
                );
                categories.put("errors", errors.toString());
                return new ResponseEntity<>(categories, HttpStatus.BAD_REQUEST);
            }else {
                categoriInterface.addCategory(category);
                categories.put("status", "success");
                return new ResponseEntity<>(categories, HttpStatus.OK);
            }

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> Update(@PathVariable() Long Id, @Valid @RequestBody Category category, BindingResult bindingResult) {
        try {
            Map<String, Object> categories = new HashMap<String, Object>();
            if (bindingResult.hasErrors()) {
                StringBuilder errors = new StringBuilder();
                bindingResult.getAllErrors().forEach(error ->
                        errors.append(error.getDefaultMessage()).append(" ")
                );
                categories.put("errors", errors.toString());
                return new ResponseEntity<>(categories, HttpStatus.BAD_REQUEST);
            }else {
                categoriInterface.addCategory(category);
                categories.put("status", "success");
                return new ResponseEntity<>(categories, HttpStatus.OK);
            }

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }
    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
       Map<String, Object> status = new HashMap<String,Object>();
       categoriInterface.deleteCategory(id);
       status.put("messages","berhasil hapus data");
       return new ResponseEntity<>(status,HttpStatus.OK);
    }

    public ResponseEntity<?> showThirdparty(){

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, " ");
//        Request request = new Request.Builder()
//                .url("http://localhost:8081/api/category/delete/1")
//                .method("DELETE", body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();

        Map<String, Object> res = new HashMap<String,Object>();
        res.put("status", "success");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
