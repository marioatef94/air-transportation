package com.transportation.repo;

import com.transportation.data.lookup.TransportationModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransportationModelRepo extends CrudRepository<TransportationModel, Long> {
    Optional<TransportationModel> findByName(String name);
}
