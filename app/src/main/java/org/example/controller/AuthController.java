package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entities.RefreshToken;
import org.example.model.UserInfoDto;
import org.example.response.JWTResponseDTO;
import org.example.service.JWTServices;
import org.example.service.RefreshTokenService;
import org.example.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

@AllArgsConstructor
@RestController
public class AuthController {
    @Autowired
    private JWTServices jwtServices;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("auth/v1/signup")
    public ResponseEntity Signup(@RequestBody UserInfoDto userInfoDto){
        try {
           //System.out.println("Received userName: " + userInfoDto.getFirstName());
            Boolean isSignUped=userDetailsServiceImpl.signUpUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignUped)){
                return  new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken=refreshTokenService.createRefreshToken(userInfoDto.getUserName());
            System.out.println(refreshToken);
            String jwtToken=jwtServices.generateToken(userInfoDto.getUserName());
            System.out.println(jwtToken);
            return new ResponseEntity<>(JWTResponseDTO.builder().accessToken(jwtToken)
                    .token(refreshToken.getToken()).build(),HttpStatus.OK);

        }catch (Exception ex){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String stackTrace = sw.toString();

            return new ResponseEntity<>(stackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
