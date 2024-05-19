package com.booking.Service;

import com.booking.Dto.LoginDto;
import com.booking.Dto.UserDto;
import com.booking.Repository.UserRepository;
import com.booking.entity.PropertyUser;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {

    private UserRepository userRepository;
    private JwtService jwtService;


    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    public PropertyUser createuser(UserDto userDto) {
        PropertyUser user=new PropertyUser();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setUserRole(userDto.getUserRole());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(10)));
        PropertyUser save = userRepository.save(user);
        return save;
    }


    public String verifylogin(LoginDto loginDto) {
        Optional<PropertyUser> user = userRepository.findByUsername(loginDto.getUsername());
        PropertyUser user1 = user.get();
        if(BCrypt.checkpw(loginDto.getPassword(), user1.getPassword())) {
            return jwtService.generateToken(user1);
        }
        return null;
    }
}

