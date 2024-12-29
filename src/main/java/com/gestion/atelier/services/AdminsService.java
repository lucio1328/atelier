package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.AdminsDTO;
import com.gestion.atelier.mappers.AdminsMapper;
import com.gestion.atelier.repository.AdminsRepository;
import com.gestion.atelier.utils.PasswordUtil;
import com.gestion.atelier.models.Admins;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminsService {

    @Autowired
    private AdminsRepository adminsRepository;

    private final AdminsMapper adminsMapper = AdminsMapper.INSTANCE;

    //
    public Admins verification(String email, String mdp) throws Exception {
        if(email == null || mdp == null) {
            if(email == null) {
                throw new Exception("Le champ email est obligatoire");
            }
            else if(mdp == null) {
                throw new Exception("Le champ mot de passe est obligatoire");
            }
        }
        Admins admin = adminsRepository.getByEmail(email);
        if(admin != null) {
            if(PasswordUtil.checkPassword(mdp, admin.getMotDePasse())) {
                return admin;
            }
        }
        return null;
    }

    //
    public AdminsDTO getById(Long id) throws Exception {
        Admins admin = adminsRepository.getById(id);
        if (admin == null) {
            throw new Exception("Administrateur introuvable");
        }
        return adminsMapper.adminsToAdminsDTO(admin);
    }

    //
    public List<AdminsDTO> getAll() {
        List<Admins> admins = adminsRepository.getAll();
        return admins.stream()
                     .map(adminsMapper::adminsToAdminsDTO)
                     .collect(Collectors.toList());
    }

    //
    public AdminsDTO createAdmin(AdminsDTO adminsDTO) {
        Admins admin = adminsMapper.adminsDTOToAdmins(adminsDTO);
        Admins savedAdmin = adminsRepository.save(admin);
        return adminsMapper.adminsToAdminsDTO(savedAdmin);
    }

    //
    public AdminsDTO updateAdmin(Long id, AdminsDTO adminsDTO) throws Exception {
        Admins existingAdmin = adminsRepository.getById(id);
        if (existingAdmin == null) {
            throw new Exception("Administrateur introuvable pour la mise à jour");
        }

        Admins adminToUpdate = adminsMapper.adminsDTOToAdmins(adminsDTO);

        Admins updatedAdmin = adminsRepository.save(adminToUpdate);

        return adminsMapper.adminsToAdminsDTO(updatedAdmin);
    }

    //
    public void deleteAdmin(Long id) throws Exception {
        Admins admin = adminsRepository.getById(id);
        if (admin == null) {
            throw new Exception("Administrateur introuvable pour suppression");
        }
        adminsRepository.deleteById(id);
    }
}
