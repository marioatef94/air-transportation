package com.transportation.data.lookup;

import com.transportation.data.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class TransportationModel extends BaseEntity {

    public TransportationModel(String name){
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "TransportationModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
