package com.myUber.myUber.services;


import com.myUber.myUber.DTO.SignUpDto;
import com.myUber.myUber.DTO.UserDTO;
import com.myUber.myUber.Entities.Enums.Role;
import com.myUber.myUber.Entities.User;
import com.myUber.myUber.Repositories.UserRepo;
import com.myUber.myUber.SEcurity.JWTService;
import com.myUber.myUber.service.impl.AuthServIMplement;
import com.myUber.myUber.service.impl.DriverServiceImple;
import com.myUber.myUber.service.impl.RiderServiceImplement;
import com.myUber.myUber.service.impl.WalletServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @Mock
    private UserRepo userRepository;

    @Mock
    private RiderServiceImplement riderService;

    @Mock
    private WalletServiceImpl walletService;

    @Mock
    private DriverServiceImple driverService;

    @Spy
    private ModelMapper modelMapper;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServIMplement authService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.RIDER));
    }

    @Test
    void testLogin_whenSuccess() {
//        arrange
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(jwtService.generateAccessToken(any(User.class))).thenReturn("accessToken");
        when(jwtService.generateRefreshToken(any(User.class))).thenReturn("refreshToken");

//        act
        String[] tokens = authService.login(user.getEmail(), user.getPassword());

//        assert
        assertThat(tokens).hasSize(2);
        assertThat(tokens[0]).isEqualTo("accessToken");
        assertThat(tokens[1]).isEqualTo("refreshToken");
    }

    @Test
    void testSignup_whenSuccess() {
        // Arrange
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
       SignUpDto signupDto = new SignUpDto();
        signupDto.setEmail("test@example.com");
        signupDto.setPassword("password");
        UserDTO userDto = authService.signup(signupDto);

        // Assert
        assertThat(userDto).isNotNull();
        assertThat(userDto.getEmail()).isEqualTo(signupDto.getEmail());
        verify(riderService).createNewRider(any(User.class));
        verify(walletService).createNewWallet(any(User.class));
    }
}