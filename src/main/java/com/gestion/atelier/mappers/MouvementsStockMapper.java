package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.MouvementsStockDTO;
import com.gestion.atelier.models.MouvementsStock;

@Mapper
public interface MouvementsStockMapper {
    MouvementsStockMapper INSTANCE = Mappers.getMapper(MouvementsStockMapper.class);

    MouvementsStockDTO mouvementsStockToMouvementsStockDTO(MouvementsStock mouvementsStock);

    MouvementsStock mouvementsStockDTOToMouvementsStock(MouvementsStockDTO mouvementsStockDTO);
}
