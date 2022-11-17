package com.transportation.data.model;

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
public abstract class Goods extends BaseEntity {
    private String code;
    private String name;
    private double weight;


    @Override
    public String toString() {
        return "Goods{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
