package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.mappers.PiecesDetacheesMapper;
import com.gestion.atelier.repository.PiecesDetacheesRepository;
import com.gestion.atelier.models.PiecesDetachees;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PiecesDetacheesService {

    @Autowired
    private PiecesDetacheesRepository piecesDetacheesRepository;

    private final PiecesDetacheesMapper piecesDetacheesMapper = PiecesDetacheesMapper.INSTANCE;

    public PiecesDetacheesDTO getById(Long id) {
        PiecesDetachees piece = piecesDetacheesRepository.getById(id);
        return piecesDetacheesMapper.piecesDetacheesToPiecesDetacheesDTO(piece);
    }

    // 
    public List<PiecesDetacheesDTO> getAll() {
        List<PiecesDetachees> pieces = piecesDetacheesRepository.getAll();
        return pieces.stream()
                     .map(piecesDetacheesMapper::piecesDetacheesToPiecesDetacheesDTO)
                     .collect(Collectors.toList());
    }

    // 
    public List<PiecesDetacheesDTO> getByNomPiece(String nomPiece) {
        List<PiecesDetachees> pieces = piecesDetacheesRepository.getByNomPiece(nomPiece);
        return pieces.stream()
                     .map(piecesDetacheesMapper::piecesDetacheesToPiecesDetacheesDTO)
                     .collect(Collectors.toList());
    }

    //
    public List<PiecesDetacheesDTO> getByReference(String reference) {
        List<PiecesDetachees> pieces = piecesDetacheesRepository.getByReference(reference);
        return pieces.stream()
                     .map(piecesDetacheesMapper::piecesDetacheesToPiecesDetacheesDTO)
                     .collect(Collectors.toList());
    }

    //
    public PiecesDetacheesDTO createPiece(PiecesDetacheesDTO piecesDetacheesDTO) {
        PiecesDetachees piece = piecesDetacheesMapper.piecesDetacheesDTOToPiecesDetachees(piecesDetacheesDTO);
        PiecesDetachees savedPiece = piecesDetacheesRepository.save(piece);
        return piecesDetacheesMapper.piecesDetacheesToPiecesDetacheesDTO(savedPiece);
    }

    //
    public PiecesDetacheesDTO updatePiece(Long id, PiecesDetacheesDTO piecesDetacheesDTO) throws Exception {
        PiecesDetachees existingPiece = piecesDetacheesRepository.getById(id);
        if (existingPiece != null) {
            existingPiece.setNomPiece(piecesDetacheesDTO.getNomPiece());
            existingPiece.setReference(piecesDetacheesDTO.getReference());
            existingPiece.setDescription(piecesDetacheesDTO.getDescription());

            PiecesDetachees updatedPiece = piecesDetacheesRepository.save(existingPiece);
            return piecesDetacheesMapper.piecesDetacheesToPiecesDetacheesDTO(updatedPiece);
        }
        throw new Exception("Update piece echoue");
    }

    //
    public void deletePiece(Long id) {
        piecesDetacheesRepository.deleteById(id);
    }
}
