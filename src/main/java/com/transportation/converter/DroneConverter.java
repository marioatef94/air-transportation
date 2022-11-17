package com.transportation.converter;

import com.transportation.data.dto.TransportationDto;
import com.transportation.data.dto.ItemsDto;
import com.transportation.data.enums.TransportationState;
import com.transportation.data.lookup.TransportationModel;
import com.transportation.data.model.Drone;
import com.transportation.data.model.Medication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class DroneConverter implements Converter<TransportationDto,Drone>{

    /******** Can Use ModelMapper Instead ******/
    public TransportationDto convertToDto(Drone entity){
        var dto = new TransportationDto();
        if(entity!=null){
            dto.setId(entity.getId());
            dto.setSerialNumber(entity.getSerialNumber());
            dto.setWeight(entity.getWeight());
            dto.setBatterCapacity(entity.getBatteryCapacity());

            var state = entity.getState();
            if(state!=null){dto.setTransportationState(state.name());}

            var model = entity.getModel();
            if(model!=null){
                dto.setTransportationModelId(model.getId());
                dto.setTransportationModelName(model.getName());
            }

            var items = entity.getItems();
            if(items!=null && items.size()>0){
                var dtoList = new ArrayList<ItemsDto>();
                items.forEach(item->{
                    var dtoItem = ItemsDto.builder()
                            .id(item.getId())
                            .image(item.getImage())
                            .name(item.getName())
                            .code(item.getCode())
                            .weight(item.getWeight())
                            .build();
                    dtoList.add(dtoItem);
                });
                dto.setItems(dtoList);
            }
        }return dto;
    }
    public Drone convertToEntity(TransportationDto dto){
        var entity = new Drone();
        if(dto!=null){
            entity.setId(dto.getId());
            entity.setWeight(dto.getWeight());
            entity.setSerialNumber(dto.getSerialNumber());
            entity.setBatteryCapacity(dto.getBatterCapacity());

            // Assume That State Enum is Correct and already exist
            entity.setState(TransportationState.valueOf(dto.getTransportationState()));

            if(dto.getTransportationModelId()!=null){
                TransportationModel modelEntity = new TransportationModel();
                modelEntity.setId(dto.getTransportationModelId());
                modelEntity.setName(dto.getTransportationModelName());
                entity.setModel(modelEntity);
            }

            var itemsDtoList = dto.getItems();
            if(itemsDtoList!=null && itemsDtoList.size()>0){
                var itemEntityList = new ArrayList<Medication>();
                itemsDtoList.forEach(itemDto->{
                    var medicationEntity = new Medication();
                    medicationEntity.setId(itemDto.getId());
                    medicationEntity.setName(itemDto.getName());
                    medicationEntity.setCode(itemDto.getCode());
                    medicationEntity.setWeight(itemDto.getWeight());
                    medicationEntity.setImage(itemDto.getImage());
                    medicationEntity.setDrone(entity);
                    itemEntityList.add(medicationEntity);
                });entity.setItems(itemEntityList);
            }
        }return entity;
    }

}
