package com.myUber.myUber.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointDTO {

    private double[] coordinate;
    private String type= "Point";
    public PointDTO(double[] coordinate){
        this.coordinate=coordinate;
    }




}
