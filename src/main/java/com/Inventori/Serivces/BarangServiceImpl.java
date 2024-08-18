package com.Inventori.Serivces;

import com.Inventori.Models.Barang;
import com.Inventori.Repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarangServiceImpl implements BarangServices {

    @Autowired
    private BarangRepository barangRepository;

    @Override
    public List<Barang> getAllBarang() {
        return barangRepository.findAll();
    }

    @Override
    public Optional<Barang> getBarangById(Long id) {
        return barangRepository.findById(id);
    }

    @Override
    public Barang saveBarang(Barang barang) {
        return barangRepository.save(barang);
    }

    @Override
    public Barang updateBarang(Barang barang) {
        Optional<Barang> existingBarang = barangRepository.findById(barang.getId());
        if (existingBarang.isPresent()) {
            Barang updatedBarang = existingBarang.get();
            updatedBarang.setId_barang(barang.getId_barang());
            updatedBarang.setKd_barang(barang.getKd_barang());
            updatedBarang.setNama_barang(barang.getNama_barang());
            updatedBarang.setHarga(barang.getHarga());
            updatedBarang.setJumlah_stok(barang.getJumlah_stok());
            updatedBarang.setId_jenisbarang(barang.getId_jenisbarang());
            updatedBarang.setCreated_at(barang.getCreated_at());
            updatedBarang.setUpdated_at(barang.getUpdated_at());
            updatedBarang.setStok_awal(barang.getStok_awal());
            updatedBarang.setStok_akhir(barang.getStok_akhir());
            updatedBarang.setStok_keluar(barang.getStok_keluar());

            return barangRepository.save(updatedBarang);
        } else {
            throw new RuntimeException("Barang not found with id " + barang.getId());
        }
    }

    @Override
    public void deleteBarang(Long id) {
        barangRepository.deleteById(id);
    }
}
