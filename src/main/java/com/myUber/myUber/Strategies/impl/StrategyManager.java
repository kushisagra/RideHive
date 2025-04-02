package com.myUber.myUber.Strategies.impl;

import com.myUber.myUber.Strategies.DriverMatchingStrategy;
import com.myUber.myUber.Strategies.RideFareCalculationStrategy;
import com.myUber.myUber.Strategies.impl.DriverMatchNearestimplement;
import com.myUber.myUber.Strategies.impl.DriverMatchRatingimpl;
import com.myUber.myUber.Strategies.impl.RideFareDeafultCal;
import com.myUber.myUber.Strategies.impl.RideFareSurgePriceCalStrateg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class StrategyManager {
    private final DriverMatchRatingimpl driverMatchRatingimpl;
    private final DriverMatchNearestimplement driverMatchNearestimplement;
    private final RideFareSurgePriceCalStrateg SurgeCalculator;
    private final RideFareDeafultCal DeafaultCAlculator;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if(riderRating>=4.8){
            return driverMatchRatingimpl;

        }
        else{
            return driverMatchNearestimplement;
        }
    }
    public RideFareCalculationStrategy rideFareCalculationStrategy(){

        //6pmTO 9Pn
        LocalTime surgeStartTime=LocalTime.of(18,0);
        LocalTime surgeEndTime=LocalTime.of(21,0);
        LocalTime currentTtime=LocalTime.now();

        boolean isSurgeTime=currentTtime.isAfter(surgeStartTime)&&currentTtime.isBefore(surgeEndTime);
        if(isSurgeTime){
            return SurgeCalculator;
        }
        else {
            return DeafaultCAlculator;
        }


    }



}
