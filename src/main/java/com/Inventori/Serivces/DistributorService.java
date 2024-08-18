package com.Inventori.Serivces;

import com.Inventori.Models.Distributor;

import java.util.List;
import java.util.Optional;
import javax.persistence.*;
public interface DistributorService {
    List<Distributor> getAllDistributor();
    Optional<Distributor> showById(Long id);
    Distributor createDistributor(Distributor distributor);
    Distributor updateDistributor(Distributor distributor);
    void deleteDistributor(Distributor distributor);

    void deleteDistributor(Long id);
}
