package com.transportation.controller;

import com.transportation.data.dto.ItemsDto;
import com.transportation.data.dto.TransportationDto;
import com.transportation.data.enums.TransportationType;
import com.transportation.service.factory.TransportationServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transportations")
public class TransportationController {
    //Prefer To Create RestResponse Entity To Have Same Structure From All APIs Response
    private final TransportationServiceFactory factory;

    @Autowired
    public TransportationController(TransportationServiceFactory factory) {
        this.factory = factory;
    }

    @PostMapping()
    public ResponseEntity<TransportationDto> registerTransportation(@Valid @RequestBody TransportationDto transportationDto){
        var service = factory.serviceFactory(TransportationType.DRONE); // May Depend On SomeLogic Or From UI Request ( Not Prefer TO Put here )
        var dto = service.registerTransportation(transportationDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/battery/{id}")
    public ResponseEntity<Double> checkTransportationBatter(@PathVariable(name = "id")Long id){
        var service = factory.serviceFactory(TransportationType.DRONE);
        var batteryCapacity = service.getTransportationBatteryCapacity(id);
        return ResponseEntity.ok(batteryCapacity);
    }

    @GetMapping("/available")
    public ResponseEntity<List<TransportationDto>> findAvailableTransportationForLoad(){
        var service = factory.serviceFactory(TransportationType.DRONE);
        var list = service.getAvailableTransportationForLoading();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<List<ItemsDto>> findItemsForTransportation(@PathVariable("id")Long transportationId){
        var service = factory.serviceFactory(TransportationType.DRONE);
        var list = service.getTransportationGoodsItems(transportationId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/items/load/{id}")
    public ResponseEntity<List<ItemsDto>> loadTransportation(@PathVariable("id") Long transportationId,
                                                             @RequestBody List<Long> itemsIds){
        var service = factory.serviceFactory(TransportationType.DRONE);
        var list = service.loadTransportation(transportationId,itemsIds);
        return ResponseEntity.ok(list);
    }



}
