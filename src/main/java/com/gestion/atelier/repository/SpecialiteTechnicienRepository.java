package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.composite.SpecialiteTechnicienId;
import com.gestion.atelier.models.SpecialiteTechnicien;

@Repository
public interface SpecialiteTechnicienRepository extends JpaRepository<SpecialiteTechnicien, SpecialiteTechnicienId> {

    @Query("SELECT st FROM SpecialiteTechnicien st " +
           "JOIN FETCH st.technicien t " +
           "JOIN FETCH st.specialite s")
    List<SpecialiteTechnicien> getAll();

    @Query("SELECT st FROM SpecialiteTechnicien st " +
           "JOIN FETCH st.technicien t " +
           "JOIN FETCH st.specialite s " +
           "WHERE t.id = :technicienId")
    SpecialiteTechnicien getByIdTechnicien(Long technicienId);

    @Query("SELECT st FROM SpecialiteTechnicien st " +
           "JOIN FETCH st.technicien t " +
           "JOIN FETCH st.specialite s " +
           "WHERE s.id = :specialiteId")
    SpecialiteTechnicien getByIdSpecialite(Long specialiteId);
}
