package com.booking.Controller;

import com.booking.Dto.LoginDto;
import com.booking.Dto.TokenHeader;
import com.booking.Dto.UserDto;
import com.booking.Service.JwtService;
import com.booking.Service.UserService;
import com.booking.entity.PropertyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService service;



    public UserController(UserService service) {
        this.service = service;

    }

@PostMapping("/addUser")
    public ResponseEntity<String> createUser( @RequestBody UserDto userDto){
    PropertyUser addUser = service.createuser(userDto);
    return new ResponseEntity<>("user registered", HttpStatus.CREATED);


}
@PostMapping("/verifylogin")
public ResponseEntity<?> VerifyLogin(@RequestBody LoginDto loginDto){

    String token = service.verifylogin(loginDto);

if(token!=null){
    TokenHeader tokenHeader=new TokenHeader();
    tokenHeader.setToken(token);
    return new ResponseEntity<>(tokenHeader,HttpStatus.OK);

    }
    return new ResponseEntity<>("Something went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
}

@PostMapping("/addCountry")
public String addcountry(){
        return "done";
}

@GetMapping("/profile")
public PropertyUser getProfile(@AuthenticationPrincipal PropertyUser user){
    return user;
    }
}