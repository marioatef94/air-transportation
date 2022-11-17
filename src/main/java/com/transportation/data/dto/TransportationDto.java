package com.transportation.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transportation.data.enums.TransportationType;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportationDto {

    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String serialNumber;

    @NotNull
    @NotEmpty
    private String transportationState;

    @Min(1)
    @Max(500)
    @NotNull
    private double weight;
    @Min(1)
    @Max(100)
    @NotNull
    private double batterCapacity;
    private Long transportationModelId;
    private String transportationModelName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TransportationType type;
    private List<ItemsDto> items;


    @Override
    public String toString() {
        return "TransportationDto{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", transportationState='" + transportationState + '\'' +
                ", weight=" + weight +
                ", batterCapacity=" + batterCapacity +
                ", transportationModelId=" + transportationModelId +
                ", transportationModelName='" + transportationModelName + '\'' +
                ", type=" + type +
                ", items=" + items +
                '}';
    }
}
