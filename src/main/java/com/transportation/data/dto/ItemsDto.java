package com.transportation.data.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsDto {

    private Long id;
    private String name;
    private String code;
    private String image;
    private double weight;

    private Long droneId;
    private String droneSerialNumber;


    @Override
    public String toString() {
        return "MedicationDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", image='" + image + '\'' +
                ", weight=" + weight +
                ", droneId=" + droneId +
                ", serialNumber='" + droneSerialNumber + '\'' +
                '}';
    }
}
