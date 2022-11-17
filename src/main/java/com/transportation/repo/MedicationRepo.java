package com.transportation.repo;

import com.transportation.data.model.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepo extends CrudRepository<Medication, Long> {}
