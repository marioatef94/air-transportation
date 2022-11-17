package com.transportation.data.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medication extends Goods{

    /**
     *  For now Will Save just image name , prefer to upload it storage provider like : AWS(S3),Azure,...
     *  and save the key for it.
     * **/
    private String image;


    /*************************
     * Relations
     ************************/
    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @Override
    public String toString() {
        return "Medication{" +
                "image='" + image + '\'' +
                '}';
    }
}
