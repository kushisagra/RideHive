package com.myUber.myUber.service.impl;

import com.myUber.myUber.service.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistServiceOSRMimplement implements DistanceService {
    private static final String OSRM_API_BASE_URL ="http://router.project-osrm.org/route/v1/driving/";
    @Override
    public double calculateDistance(Point src, Point dest) {

        try {
            String uri=src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();

            OSRMResponseDTO responseDTO = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDTO.class);

            return responseDTO.getRoutes().get(0).getDistance() / 1000;
        }
        catch (Exception e){
            throw new RuntimeException("error getting data from osrm"+e.getMessage());
        }



    }
}

@Data
class OSRMResponseDTO{
    private List<OSRMRoute> routes;


}
@Data
class OSRMRoute{
    private double distance;
}
