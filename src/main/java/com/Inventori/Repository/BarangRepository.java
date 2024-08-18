package com.Inventori.Repository;

import com.Inventori.Models.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Long> {
    // Anda bisa menambahkan metode query kustom di sini jika diperlukan
}
