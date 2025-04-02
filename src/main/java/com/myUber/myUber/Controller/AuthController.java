package com.myUber.myUber.Controller;

import com.myUber.myUber.DTO.*;
import com.myUber.myUber.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")

    ResponseEntity<UserDTO> signUp(@RequestBody SignUpDto signUpDto) {

        return new ResponseEntity<>(authService.signup(signUpDto), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
        @PostMapping("/onBoardNewDriver/{userID}")
                ResponseEntity<DriverDTO> onBoardNewDriver(@PathVariable Long userId,
                                                           @RequestBody OnboardDriverDTO onboardDriverDTO){
        return new ResponseEntity<>(authService.onBoardNewDriver(userId,onboardDriverDTO.getVehicleId()),HttpStatus.CREATED);


        }

        @PostMapping("/login")
    ResponseEntity<LoginResponseDTO>  login(@RequestBody LoginRequestDTO loginRequestDTO,
                                            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String tokens[]= authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
            Cookie cookie=new Cookie("token",tokens[1]);
            cookie.setHttpOnly(true);//js cant access cookie

            httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
        }




    }
