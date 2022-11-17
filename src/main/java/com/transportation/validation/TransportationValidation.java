package com.transportation.validation;

import com.transportation.data.dto.TransportationDto;

public interface TransportationValidation {

    Boolean isRegisterTransportationValid(TransportationDto dto);

    Boolean isLoadTransportationValid(TransportationDto dto, double weight);


}
