package com.transportation.data.model;

import com.transportation.data.lookup.TransportationModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drone extends Transportation {

    @Column(name = "battery_capacity")
    private double batteryCapacity;

    /*************************
     * Relations
     ************************/
    @ManyToOne
    @JoinColumn(name = "model_id")
    private TransportationModel model;

    @OneToMany(targetEntity = Medication.class, fetch = FetchType.LAZY, mappedBy = "drone")
    private List<Medication> items;

    @Override
    public String toString() {
        return "Drone{" +
                "batteryCapacity=" + batteryCapacity +
                ", model=" + model +
                ", items=" + items +
                '}';
    }
}
