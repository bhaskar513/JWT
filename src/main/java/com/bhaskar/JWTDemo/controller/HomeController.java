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
        return "welcome security Bhaskar";
    }

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{
        try {
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
