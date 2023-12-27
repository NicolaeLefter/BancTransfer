package com.example.BancTransfer.configurationSpringSecurity;

import com.example.BancTransfer.exception.BalanceNegativException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
   @PostMapping("/save")
    public void saveUser(@RequestBody User user){
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         userRepository.save(user);
    }
    @GetMapping("/getAll")
    public List<User> getAllUser(){
       return userRepository.findAll();
    }

}
