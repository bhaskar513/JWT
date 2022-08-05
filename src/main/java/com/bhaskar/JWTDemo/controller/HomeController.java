package com.bhaskar.JWTDemo.controller;


import com.bhaskar.JWTDemo.model.JWTRequest;
import com.bhaskar.JWTDemo.model.JWTResponse;
import com.bhaskar.JWTDemo.service.UserService;
import com.bhaskar.JWTDemo.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){
        System.out.println("inside get details");
        return "welcome security Bhaskar";
    }

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{
        try {
            System.out.println("inside authentication");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    ));
        }
        catch (BadCredentialsException e){
            throw new Exception("Invalid crediential", e);
    }

    final UserDetails userDetails
            = userService.loadUserByUsername(jwtRequest.getUsername());

    final String token =
            jwtUtility.generateToken(userDetails);

        return  new JWTResponse(token);
        }
}
