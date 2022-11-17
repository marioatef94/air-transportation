package com.transportation.service;

import com.transportation.data.dto.TransportationDto;
import com.transportation.data.dto.ItemsDto;

import java.util.List;

public interface TransportationService {

    TransportationDto registerTransportation(TransportationDto dto);

    List<ItemsDto> loadTransportation(Long transportationId,List<Long> itemsIds);

    List<TransportationDto> getAvailableTransportationForLoading();

    List<ItemsDto> getTransportationGoodsItems(Long transportationId);

    Double getTransportationBatteryCapacity(Long transportationId);
}
