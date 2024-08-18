package com.Inventori.Serivces;

import com.Inventori.Models.ClusteringResult;

import javax.swing.text.html.Option;
import java.util.*;

public interface ClusteringInterface {

    List<ClusteringResult> getAll();
    Optional<ClusteringResult> getDetail(Long id);
    ClusteringResult updateDetail(Long id, ClusteringResult detail);
    ClusteringResult addDetail(ClusteringResult detail);
    void deleteDetail(Long id);

}
