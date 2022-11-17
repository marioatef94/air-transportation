package com.transportation.service;

import com.transportation.converter.DroneConverter;
import com.transportation.converter.MedicationConverter;
import com.transportation.data.dto.TransportationDto;
import com.transportation.data.dto.ItemsDto;
import com.transportation.data.model.Drone;
import com.transportation.repo.DroneRepo;
import com.transportation.repo.MedicationRepo;
import com.transportation.util.StringUtil;
import com.transportation.validation.DroneValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DRONE")
public class DroneService implements TransportationService{

    private final DroneRepo droneRepo;
    private final DroneConverter droneConverter;
    private final MedicationConverter medicationConverter;

    private final MedicationRepo medicationRepo;
    private final DroneValidation droneValidation;

    @Autowired
    public DroneService(DroneRepo droneRepo, DroneConverter droneConverter, MedicationConverter medicationConverter, MedicationRepo medicationRepo, DroneValidation droneValidation) {
        this.droneRepo = droneRepo;
        this.droneConverter = droneConverter;
        this.medicationConverter = medicationConverter;
        this.medicationRepo = medicationRepo;
        this.droneValidation = droneValidation;
    }

    @Override
    public TransportationDto registerTransportation(TransportationDto dto) {
        dto.setId(null);
        var serialNumber = StringUtil.generateRandomString(50);
        dto.setSerialNumber(serialNumber);
        var isValid = droneValidation.isRegisterTransportationValid(dto);
        if(isValid){
            var entity = droneConverter.convertToEntity(dto);
            var savedDrone = droneRepo.save(entity);
            return droneConverter.convertToDto(savedDrone);
        }throw new RuntimeException("Validations Required"); // Prefer To throw custom exception with list of errors
    }

    @Override
    public List<ItemsDto> loadTransportation(Long transportationId,List<Long> itemsIds) {
        var drone = isDronePresent(transportationId);
        var droneDto = droneConverter.convertToDto(drone);

        var listToReturn = new ArrayList<ItemsDto>();
        itemsIds.forEach(id -> {
            var optionalMedication = medicationRepo.findById(id);
            if(optionalMedication.isPresent()){
                var entity = optionalMedication.get();
                var itemWeight = entity.getWeight();
                var isValid = droneValidation.isLoadTransportationValid(droneDto,itemWeight);
                if(isValid){
                    entity.setDrone(drone);
                    var savedEntity = medicationRepo.save(entity); // Prefer To Separate in Another Layer or Facade
                    changeDroneWeightLogic(drone,itemWeight);
                    listToReturn.add(medicationConverter.convertToDto(savedEntity));
                }else throw new RuntimeException("Cannot Load Drone");



            }else throw new RuntimeException("No Found Item With ID : " + id);
        });
        return listToReturn;
    }

    @Override
    public List<TransportationDto> getAvailableTransportationForLoading() {
        var availableDroneList = droneRepo.findAvailableDronesForLoad();
        var dtoList = new ArrayList<TransportationDto>();
        availableDroneList.forEach(drone->{
            var dto = droneConverter.convertToDto(drone);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public List<ItemsDto> getTransportationGoodsItems(Long transportationId) {
        var drone = isDronePresent(transportationId);
        var itemsList = drone.getItems();
        var dtoList = new ArrayList<ItemsDto>();
        itemsList.forEach(item->{
            var dto = medicationConverter.convertToDto(item);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public Double getTransportationBatteryCapacity(Long transportationId) {
        return droneRepo.findBatterCapacityById(transportationId);
        // Prefer to throw new Custom exception if Transportation Not Found
    }


    private Drone isDronePresent(Long transportationId){
        var drone = droneRepo.findById(transportationId);
        if(drone.isEmpty()) throw new RuntimeException("Drone is not exist");
        return drone.get();
    }

    private void changeDroneWeightLogic(Drone drone, double weight){
        drone.setWeight(weight + drone.getWeight());
        droneRepo.save(drone);
    }


}
