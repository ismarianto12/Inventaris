package com.Inventori.Controllers;

import com.Inventori.Models.Distributor;
import com.Inventori.Repository.DistributorRepository;
import com.Inventori.Serivces.DistributorServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/distributor")

public class DistributorController {

    @Autowired
    DistributorServiceimpl distributorRepository;
    @Autowired
    DistributorRepository distributorRepositorypart;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        try {
            Map<String, Object> res = new HashMap<String, Object>();
            List<Distributor> data = distributorRepositorypart.findAll();
            res.put("status", "OK");
            res.put("data", data);

            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResponseEntity<String> insert(@RequestBody Distributor distributor) {

        Map<String, Object> objectmapper = new HashMap<String, Object>();

        try {
            distributorRepository.createDistributor(distributor);
            objectmapper.put("status", "OK");
            return ResponseEntity.status(HttpStatus.CREATED).body(objectmapper.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Distributor distributor, @PathVariable Long id) {
        Map<String, Object> objectmapping = new HashMap<>();
        try {
            distributor.setId(id);
            distributorRepository.createDistributor(distributor);
            objectmapping.put("message", "data updated successfully");
            objectmapping.put("status", "OK");
            return new ResponseEntity<>(objectmapping, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> detaildata(@PathVariable Long id) {
        Optional<Distributor> data = distributorRepository.showById(id);
        if (data.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Distributor not found");
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Distributor distributor, @PathVariable Long id) {
        distributorRepository.deleteDistributor(id);
        Map<String, Object> objectmapper = new HashMap<>();
        objectmapper.put("status", "OK");
        objectmapper.put("message", "data deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(objectmapper);
    }

}
