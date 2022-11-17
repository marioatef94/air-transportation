package com.transportation.validation;

import com.transportation.data.dto.TransportationDto;
import com.transportation.repo.DroneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DroneValidation implements TransportationValidation{

    private final static Double MAX_WEIGHT = 500.0;

    private final DroneRepo droneRepo;

    @Autowired
    public DroneValidation(DroneRepo droneRepo) {
        this.droneRepo = droneRepo;
    }

    @Override
    public Boolean isRegisterTransportationValid(TransportationDto dto) {
        var isValid = true;
        var transportationSerialNumber = dto.getSerialNumber();
        if(transportationSerialNumber!=null){
            var savedDrone = droneRepo.findBySerialNumber(transportationSerialNumber);
            if(savedDrone.isPresent()){isValid = false;}
        }
        return isValid;
    }

    @Override
    public Boolean isLoadTransportationValid(TransportationDto dto, double weight) {
        var currentDroneWeight = dto.getWeight();
        var batteryCapacity = dto.getBatterCapacity();
        return ((currentDroneWeight + weight)<= MAX_WEIGHT) && (batteryCapacity>= 25);
    }
}
