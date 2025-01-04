package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.composite.SpecialiteTechnicienId;
import com.gestion.atelier.models.SpecialiteTechnicien;
import com.gestion.atelier.models.Specialites;

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
       List<SpecialiteTechnicien> getByTechnicien(Long technicienId);

       @Query("SELECT st FROM SpecialiteTechnicien st " +
              "JOIN FETCH st.technicien t " +
              "JOIN FETCH st.specialite s " +
              "WHERE s.id = :specialiteId")
       SpecialiteTechnicien getByIdSpecialite(Long specialiteId);

       // @Query("SELECT st FROM SpecialiteTechnicien st " +
       //     "JOIN FETCH st.technicien t " +
       //     "JOIN FETCH st.specialite s " +
       //     "WHERE t.id = :technicienId and s.id = :specialiteId")
       // SpecialiteTechnicien getByIdTechnicienAndSpecialite(Long technicienId, Long specialiteId);
}
