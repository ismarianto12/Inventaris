package com.Inventori.Serivces;

import com.Inventori.Models.Barang;

import java.util.List;
import java.util.Optional;

public interface BarangServices {

    List<Barang> getAllBarang();

    Optional<Barang> getBarangById(Long id);

    Barang saveBarang(Barang barang);
    Barang updateBarang(Barang barang);

    void deleteBarang(Long id);
}
