package com.transportation.converter;

import com.transportation.data.dto.ItemsDto;
import com.transportation.data.model.Drone;
import com.transportation.data.model.Medication;
import org.springframework.stereotype.Component;

@Component
public class MedicationConverter implements Converter<ItemsDto, Medication> {

    /******** Can Use ModelMapper Instead ******/
    @Override
    public ItemsDto convertToDto(Medication entity) {
        var dto = new ItemsDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCode(entity.getCode());
            dto.setImage(entity.getImage());
            dto.setWeight(entity.getWeight());

            var drone = entity.getDrone();
            if(drone!=null){
                dto.setDroneId(drone.getId());
                dto.setDroneSerialNumber(drone.getSerialNumber());
            }
        }return dto;
    }

    @Override
    public Medication convertToEntity(ItemsDto dto) {
        var entity = new Medication();
        if(dto!=null){
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setCode(dto.getCode());
            entity.setImage(dto.getImage());
            entity.setWeight(dto.getWeight());

            if(dto.getDroneId()!=null){
                var drone = new Drone();
                drone.setId(dto.getDroneId());
                entity.setDrone(drone);
            }
        }return entity;
    }
}
