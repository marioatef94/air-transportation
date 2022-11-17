package com.transportation.bootstrap;

import com.transportation.data.enums.TransportationState;
import com.transportation.data.lookup.TransportationModel;
import com.transportation.data.model.Drone;
import com.transportation.data.model.Medication;
import com.transportation.repo.DroneRepo;
import com.transportation.repo.MedicationRepo;
import com.transportation.repo.TransportationModelRepo;
import com.transportation.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataLoader {

    private static final String SERIAL_NUMBER = StringUtil.generateRandomString(50);
    private final TransportationModelRepo modelRepo;
    private final MedicationRepo medicationRepo;
    private final DroneRepo droneRepo;

    @Autowired
    public DataLoader(TransportationModelRepo modelRepo, MedicationRepo medicationRepo, DroneRepo droneRepo) {
        this.modelRepo = modelRepo;
        this.medicationRepo = medicationRepo;
        this.droneRepo = droneRepo;
    }


    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initTransportationModelData();
        initDrone();
        initMedicationItems();
    }

    private void initTransportationModelData(){
        var model1 = new TransportationModel("Lightweight");
        var model2 = new TransportationModel("Middleweight");
        var model3 = new TransportationModel("Cruiserweight");
        var model4 = new TransportationModel("Heavyweight");

        var list = new ArrayList<TransportationModel>();
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);
        modelRepo.saveAll(list);
    }

    private void initDrone(){
        var model = modelRepo.findByName("Lightweight");
        if(model.isPresent()){
            var drone = new Drone();
            drone.setSerialNumber(SERIAL_NUMBER);
            drone.setBatteryCapacity(50.0);
            drone.setWeight(120.0);
            drone.setState(TransportationState.LOADING);
            drone.setModel(model.get());
            droneRepo.save(drone);
        }
    }

    private void initMedicationItems(){
//        var drone = droneRepo.findBySerialNumber(SERIAL_NUMBER);
//        if(drone.isPresent()){
            for(int i=0;i<5;i++){
                var entity = new Medication();
                entity.setName(StringUtil.generateRandomString(4));
                entity.setCode(StringUtil.generateRandomString(10).toUpperCase() + "_CODE");
                entity.setImage(StringUtil.generateRandomString(5));
                entity.setWeight(i+30);
//                entity.setDrone(drone.get());
                medicationRepo.save(entity);
            }
//        }
    }

}
