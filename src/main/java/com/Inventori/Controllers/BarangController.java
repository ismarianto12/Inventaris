package com.Inventori.Controllers;

import com.Inventori.Models.Barang;
import com.Inventori.Repository.BarangRepository;
import com.Inventori.Serivces.BarangServices;
import com.Inventori.Serivces.FileUpload;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/barang")
public class BarangController {

    @Autowired
    private BarangServices barangServices;
    @Autowired
    private BarangRepository barangRepository;

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam Map<String, Object> obj) {
        Map<String, Object> data = new HashMap<>();
        if (!obj.containsKey("param")) {
            try {
                List<Barang> param = barangServices.getAllBarang();
                data.put("data", param);
                data.put("http", HttpStatus.OK);
                data.put("status", param.toArray().length);
                data.put("message", "Data Response Successfull");
                return ResponseEntity.ok(data);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            data.put("param", "param is null");
            data.put("message", "param is null");
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(Barang barang) {
        try {
            barangServices.saveBarang(barang);
            Map<String, Object> data = new HashMap<>();
            data.put("data", barang);
            data.put("http", HttpStatus.OK);
            data.put("status", HttpStatus.CREATED);
            data.put("message", "Data Response Successfull");
            return ResponseEntity.ok(data);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Barang> data = barangServices.getBarangById(id);
        if (data.isEmpty()) {
            return ResponseEntity.badRequest().body("No Data Found");
        } else {
            return ResponseEntity.ok(data);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, Barang barang) {
        try {
            barang.setId(id);
            Barang updatebarang = barangServices.updateBarang(barang);
            Map<String, Object> data = new HashMap<>();
            data.put("data", updatebarang);
            data.put("http", HttpStatus.OK);
            data.put("status", HttpStatus.OK);
            data.put("message", "Data Response Successfull");
            return ResponseEntity.ok(data);

        } catch (Exception e) {
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

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/barangbycat", method = RequestMethod.GET)
    public ResponseEntity<?> barangbycat() {
        Map<String, Object> objecmapper = new HashMap<>();
        try {
            objecmapper.put("data", barangRepository.fetchBarangWithCategory());
            objecmapper.put("message", "data response");
            return ResponseEntity.ok(objecmapper);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/upload", method = RequestMethod.POST,consumes = {"multipart/form-data"})
    public ResponseEntity<?> upload(@RequestParam("file")
                                        MultipartFile file, ModelMap modelMap) throws IOException {
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("File is empty");
//        }
//        String getfilename = file.getOriginalFilename();
//        String getExtfilename = getfilename.substring(getfilename.lastIndexOf('.')+ 1);
//        String renameFilename =
//       // String fileExt =
        //try {
            System.out.println(modelMap.get("judul").toString()+"get log upload ");
            FileUpload fileUpload = new FileUpload();
            fileUpload.uploadFile(file, "barang.jpg");
            return ResponseEntity.ok().body("berhasil upload file");


       // } catch (Exception e) {
        //    return ResponseEntity.status(400).body(e.getMessage());
       // }

    }

}
