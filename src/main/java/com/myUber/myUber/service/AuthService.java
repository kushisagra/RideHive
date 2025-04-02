package com.myUber.myUber.service;

import com.myUber.myUber.DTO.DriverDTO;
import com.myUber.myUber.DTO.SignUpDto;
import com.myUber.myUber.DTO.UserDTO;

public interface AuthService {


    //access +refreshToken

    String [] login(String email,String password);
    UserDTO signup(SignUpDto signUpDto);

    DriverDTO onBoardNewDriver(Long UserId,String vehicleId);


}
