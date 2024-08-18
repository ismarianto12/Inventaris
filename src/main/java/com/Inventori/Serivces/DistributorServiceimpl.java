package com.Inventori.Serivces;

import com.Inventori.Models.Distributor;
import com.Inventori.Repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DistributorServiceimpl implements DistributorService {

    @Autowired
    private DistributorRepository distributorRepository;

    @Override
    public List<Distributor> getAllDistributor(){
       return distributorRepository.findAll();
    }

    @Override
    public Optional<Distributor> showById(Long id) {
      return distributorRepository.findById(id);
    }

    @Override
    public Distributor createDistributor(Distributor distributor) {
      return distributorRepository.save(distributor);
    }

    @Override
    public Distributor updateDistributor(Distributor distributor) {
        return null;
    }

    @Override
    public void deleteDistributor(Distributor distributor) {

    }

    @Override
    public void deleteDistributor(Long id) {
       distributorRepository.deleteById(id);
    }
}
