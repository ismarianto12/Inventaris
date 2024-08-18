package com.Inventori.Models;


import javax.persistence.*;

@Entity
@Table(name = "clustering_result")
@EntityListeners(EntityListeners.class)

public class ClusteringResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String kd_barang;
    public String id_barang;
    public String hasil_cluster;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKd_barang() {
        return kd_barang;
    }

    public void setKd_barang(String kd_barang) {
        this.kd_barang = kd_barang;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getHasil_cluster() {
        return hasil_cluster;
    }

    public void setHasil_cluster(String hasil_cluster) {
        this.hasil_cluster = hasil_cluster;
    }
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public ClusteringResult(){

    }

    public String keterangan;
    public String created_at;
    public String updated_at;
    public String user_id;


}
