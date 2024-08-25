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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/barang")
public class BarangController {

    @Autowired
    private BarangServices barangServices;
    @Autowired
    private BarangRepository barangRepository;

    private String dateformatapp;
    private String param;

    BarangController() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.dateformatapp = dateFormat.format(currentDate);
    }

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
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<?> upload(@RequestParam("file")
                                    MultipartFile file, @RequestParam("title") String mapper) throws IOException {
        System.out.println(mapper.toString() + "title response page");
        Logger.getLogger(mapper.toString() + "response title");

        System.out.println(dateformatapp);


        FileUpload fileUpload = new FileUpload();
        String extension = fileUpload.getFileExtension(file.getOriginalFilename());
        Map<String, Object> data = new HashMap<>();

        if (!extension.contentEquals("png") && !extension.contentEquals("jpg") && !extension.contentEquals("pdf")) {
            data.put("message", "jenis file tidak diizinkan");
            return ResponseEntity.ok().body(data);
        }
        data.put("title", mapper);
        data.put("message", "data berhasil disimpan");
        data.put("dateresponse",this.dateformatapp);

        fileUpload.uploadFile(file, "barang.jpg");
        return ResponseEntity.ok().body(data);

    }

}
