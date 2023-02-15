package com.blog.blogappapi.controllers;


import com.blog.blogappapi.exceptions.ApiException;
import com.blog.blogappapi.payloads.JwtAuthRequest;
import com.blog.blogappapi.payloads.JwtAuthResponse;
import com.blog.blogappapi.payloads.UserDto;
import com.blog.blogappapi.security.JwtTokenHelper;
import com.blog.blogappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//fouth step of jwt Authentication

@RestController
@RequestMapping("/api/v1.0")
public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)
    {

        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
           try {
               this.authenticationManager.authenticate(authenticationToken);
           }catch (BadCredentialsException e){
               System.out.print("Invalid Details");
               throw  new ApiException("Invalid username or password");
           }
    }

    
    //register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){

        UserDto registeredUser = this.userService.registerNewUser(userDto);

        return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
    }
}
