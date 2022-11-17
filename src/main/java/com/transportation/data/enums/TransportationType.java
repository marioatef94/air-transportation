package com.transportation.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;


/*** In Case Of We Want To Add Another Type for Transportation
 *  Drone,....
 * **/
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TransportationType {
    DRONE,
    OTHER;
}
