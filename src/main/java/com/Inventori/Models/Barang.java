package com.Inventori.Models;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "barang")
@EntityListeners(AuditingEntityListener.class)
public class Barang {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String id_barang;
    private String kd_barang;
    private String nama_barang;
    private String harga;
    private String jumlah_stok;
    private String id_jenisbarang;
    private String created_at;
    private String updated_at;
    private String stok_awal;
    private String stok_akhir;
    private String stok_keluar;

    public Barang() {
    }

    public String getId_jenisbarang() {
        return id_jenisbarang;
    }

    public void setId_jenisbarang(String id_jenisbarang) {
        this.id_jenisbarang = id_jenisbarang;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getKd_barang() {
        return kd_barang;
    }

    public void setKd_barang(String kd_barang) {
        this.kd_barang = kd_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah_stok() {
        return jumlah_stok;
    }

    public void setJumlah_stok(String jumlah_stok) {
        this.jumlah_stok = jumlah_stok;
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

    public String getStok_awal() {
        return stok_awal;
    }

    public void setStok_awal(String stok_awal) {
        this.stok_awal = stok_awal;
    }

    public String getStok_akhir() {
        return stok_akhir;
    }

    public void setStok_akhir(String stok_akhir) {
        this.stok_akhir = stok_akhir;
    }

    public String getStok_keluar() {
        return stok_keluar;
    }

    public void setStok_keluar(String stok_keluar) {
        this.stok_keluar = stok_keluar;
    }


}
