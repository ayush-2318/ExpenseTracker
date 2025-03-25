package org.example.controller;

import org.example.entities.RefreshToken;
import org.example.request.AuthRequest;
import org.example.request.RefreshTokenRequest;
import org.example.response.JWTResponseDTO;
import org.example.service.JWTServices;
import org.example.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TokenController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JWTServices jwtServices;

    @PostMapping("auth/v1/login")
    public ResponseEntity AuthenticateAndGetToekn(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUserName());
            return new ResponseEntity<>(JWTResponseDTO.builder()
                    .accessToken(jwtServices.generateToken(authRequest.getUserName()))
                    .token(refreshToken.getToken())
                    .build(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Exception in User service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("auth/v1/refreshtoken")
    public JWTResponseDTO refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtServices.generateToken(userInfo.getUserName());
                    return JWTResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken()).build();

                }).orElseThrow(() -> new RuntimeException("Refresh token is not in db"));

    }


}
