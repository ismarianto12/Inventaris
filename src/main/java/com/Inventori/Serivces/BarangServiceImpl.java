package com.Inventori.Serivces;

import com.Inventori.Models.Barang;
import com.Inventori.Models.Category;
import com.Inventori.Models.JenisBarang;
import com.Inventori.Repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class BarangServiceImpl implements BarangServices {

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private EntityManager entityManager;

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

//    @Override
//    public List<Object[]> fetchBarangWithCategory() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
//
//        Root<Barang> barang = query.from(Barang.class);
////        Join<Barang, JenisBarang> category = barang.join("jenis_barang"); // assuming the field name in Barang is 'category'
//        Join<Barang, Category> category = barang.join("category");
//        query.multiselect(
//                category.get("jenis_barang"),
//                barang.get("namaBarang"),
//                barang.get("kdBarang"),
//                barang.get("harga"),
//                barang.get("jumlahStok")
//        );
//
//        query.where(cb.equal(barang.get("id_jenisbarang"), category.get("id")));
//        return entityManager.createQuery(query).getResultList();
//    }



//    @Override
//    public static List<JenisBarang> getFromTable(String name,String surname) {
//        EntityManager em = PersistenceManager.instance().createEntityManager();
//
//        try {
//            String sql = " select * from table where 1=1 ";
//            if(name!=null && !name.trim().isEmpty()){
//                sql +=" and name = :name";
//            }
//            if(surname!=null && !surname.trim().isEmpty()){
//                sql +=" and surname = :surname";
//            }
//
//            Query q = em.createNativeQuery(sql);
//            if(name!=null && !name.trim().isEmpty()){
//                q.setParameter("name", name);
//            }
//            if(surname!=null && !surname.trim().isEmpty()){
//                q.setParameter("surname", surname);
//            }
//
//            List<YourEntity> l = q.getResultList();
//            return l;
//        } finally {
//            em.close();
//        }
//    }

    @Override
    public void deleteBarang(Long id) {
        barangRepository.deleteById(id);
    }
}
