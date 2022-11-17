package com.transportation.service.factory;

import com.transportation.data.enums.TransportationType;
import com.transportation.service.DroneService;
import com.transportation.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransportationServiceFactory {

    private final DroneService droneService;

    @Autowired
    public TransportationServiceFactory(DroneService droneService) {
        this.droneService = droneService;
    }

    public TransportationService serviceFactory(TransportationType type){
        if (type == TransportationType.DRONE) {
            return droneService;
        }else return null; // Prefer to throw custom exception
    }


}
