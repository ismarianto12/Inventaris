package com.Inventori.Controllers;

import com.Inventori.Repository.JenisBarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
@RequestMapping("api/jenisbarang")
public class JenisBarang {
    @Autowired
    private JenisBarangRepository jenisBarangRepository;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        Map<String, Object> objectMap = new HashMap<String, Object>();

        try {
            objectMap.put("response", "Data load successful");
            return ResponseEntity.ok(objectMap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Map<String, Object> objectMap = new HashMap<>();
        try {
            Optional<com.Inventori.Models.JenisBarang> data = jenisBarangRepository.findById(id);
            objectMap.put("response", data);
            objectMap.put("response", "Data load successful");
            return ResponseEntity.ok(objectMap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody JenisBarang jenisBarang) {
        Map<String, Object> objectMap = new HashMap<>();
        try {
//            JenisBarang.setId(id);
//             jenisBarangRepository.save(jenisBarang);
//
//            objectMap.put("response", data);
//            objectMap.put("response", "Data load successful");
            return ResponseEntity.ok(objectMap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

 }
