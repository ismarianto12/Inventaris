package com.Inventori.Repository;

import com.Inventori.Models.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface BarangRepository extends JpaRepository<Barang, Long> {
    // Anda bisa menambahkan metode query kustom di sini jika diperlukan
 @Query(value = "SELECT\n" +
         "\tcategory.category,\n" +
         "\tbarang.nama_barang,\n" +
         "\tbarang.kd_barang,\n" +
         "\tbarang.harga,\n" +
         "\tbarang.jumlah_stok \n" +
         "FROM\n" +
         "\tbarang\n" +
         "\tINNER JOIN category ON barang.id_jenisbarang = category.id",
         nativeQuery = true)
    List<Map<String, Object>> fetchBarangWithCategory();
}
