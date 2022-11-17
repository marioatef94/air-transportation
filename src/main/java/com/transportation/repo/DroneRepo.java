package com.transportation.repo;

import com.transportation.data.model.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepo extends CrudRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);

    @Query("SELECT d.batteryCapacity FROM Drone d WHERE d.id=?1")
    Double findBatterCapacityById(Long id);

    @Query("SELECT d FROM Drone d WHERE " +
            "(d.state = com.transportation.data.enums.TransportationState.IDLE OR d.state= com.transportation.data.enums.TransportationState.LOADING)" +
            " AND d.batteryCapacity>25 ")
    List<Drone> findAvailableDronesForLoad();
}
