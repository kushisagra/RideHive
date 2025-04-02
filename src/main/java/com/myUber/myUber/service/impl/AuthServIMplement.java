package com.myUber.myUber.service.impl;

import com.myUber.myUber.DTO.DriverDTO;
import com.myUber.myUber.DTO.SignUpDto;
import com.myUber.myUber.DTO.UserDTO;
import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Enums.Role;
import com.myUber.myUber.Entities.User;
import com.myUber.myUber.Repositories.UserRepo;
import com.myUber.myUber.SEcurity.JWTService;
import com.myUber.myUber.exception.ResourceNotFoundException;
import com.myUber.myUber.service.AuthService;
import com.myUber.myUber.service.DriverService;
import com.myUber.myUber.service.RiderService;
import com.myUber.myUber.service.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServIMplement implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
private final AuthenticationManager authenticationManager;
private final JWTService jwtService;
    @Override
    public String[] login(String email, String password) {

Authentication authentication= authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email,password)
);
User user=(User) authentication.getPrincipal();

String accessToken=jwtService.generateAccessToken(user);
String refreshToken=jwtService.generateRefreshToken(user);
        return new String[]{accessToken,refreshToken};


    }

    @Override
    @Transactional
    public UserDTO signup(SignUpDto signUpDto) {

       User user=userRepo.findByEmail(signUpDto.getEmail()).orElse(null);
           if(user!=null){
               throw new RuntimeException("CANNOT SIGN UP , USER ALREADY EXIST WITH " +signUpDto.getEmail() +"EMAIL");

           }


       User MappedUser=modelMapper.map(signUpDto, User.class);
        MappedUser.setRoles(Set.of(Role.RIDER));
        MappedUser.setPassword(passwordEncoder.encode(MappedUser.getPassword()));
        User savedUser=userRepo.save(MappedUser);
//create user related entities
       riderService.createNewRider(savedUser);
       walletService.createNewWallet(savedUser);



        return modelMapper.map(savedUser, UserDTO.class);


    }

    @Override
    public DriverDTO onBoardNewDriver(Long UserId, String vehicleId) {
        User user=userRepo.findById(UserId)
                .orElseThrow(()->new ResourceNotFoundException("user not found with"+UserId));


        if(user.getRoles().contains(Role.DRIVER))throw new RuntimeException("user with "+UserId+"is already adriver");
        Driver createNewDriver=Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(Role.DRIVER);
        userRepo.save(user);

       Driver savedDriver= driverService.createNewDriver(createNewDriver);
   return modelMapper.map(savedDriver,DriverDTO.class);
    }
}
