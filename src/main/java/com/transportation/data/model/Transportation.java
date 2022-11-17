package com.transportation.data.model;

import com.transportation.data.enums.TransportationState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Transportation extends BaseEntity{
    private String serialNumber;
    private double weight;
    private TransportationState state;

    @Override
    public String toString() {
        return "Transportation{" +
                "serialNumber='" + serialNumber + '\'' +
                ", weight=" + weight +
                ", state=" + state +
                '}';
    }
}
