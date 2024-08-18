package com.Inventori.Controllers;
import com.Inventori.Models.Barang;
import com.Inventori.Serivces.BarangServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/barang")
public class BarangController {

    @Autowired
    private BarangServices barangServices;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        try {
            Map<String, Object> data = new HashMap<>();
            List<Barang> param = barangServices.getAllBarang();
            data.put("data", param);
            data.put("http", HttpStatus.OK);
            data.put("status", param.toArray().length);
            data.put("message", "Data Response Successfull");
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/insert")
    public ResponseEntity<?> insert(Barang barang) {
        try{
            barangServices.saveBarang(barang);
            Map<String, Object> data = new HashMap<>();
            data.put("data", barang);
            data.put("http", HttpStatus.OK);
            data.put("status", HttpStatus.CREATED);
            data.put("message", "Data Response Successfull");
            return ResponseEntity.ok(data);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Barang> data = barangServices.getBarangById(id);
        if (data.isEmpty()) {
            return ResponseEntity.badRequest().body("No Data Found");
        }else{
            return ResponseEntity.ok(data);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,Barang barang) {
        try{
              barang.setId(id);
              Barang updatebarang = barangServices.updateBarang(barang);
              Map<String, Object> data = new HashMap<>();
              data.put("data", updatebarang);
              data.put("http", HttpStatus.OK);
              data.put("status", HttpStatus.OK);
              data.put("message", "Data Response Successfull");
              return ResponseEntity.ok(data);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {
            barangServices.deleteBarang(id);
            Map<String, Object> data = new HashMap<>();
            data.put("data", id);
            data.put("http", HttpStatus.OK);
            data.put("status", HttpStatus.OK);
            data.put("message", "Data Response Successfull");
            return ResponseEntity.ok(data);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
