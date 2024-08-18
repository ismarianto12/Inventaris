package com.Inventori.Serivces;

import com.Inventori.Models.ClusteringResult;
import com.Inventori.Repository.ClusteringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClusteringService implements ClusteringInterface{
    @Autowired
    ClusteringRepository clusteringRepository;


    @Override
    public List<ClusteringResult> getAll() {
        return List.of();
    }

    @Override
    public Optional<ClusteringResult> getDetail(Long id) {
        return Optional.empty();
    }

    @Override
    public ClusteringResult updateDetail(Long id, ClusteringResult detail) {
        return null;
    }

    @Override
    public ClusteringResult addDetail(ClusteringResult detail) {
        return null;
    }

    @Override
    public void deleteDetail(Long id) {

    }
}
