package com.xpring.edu.services;

import java.util.List;

import com.xpring.edu.model.User;
import com.xpring.edu.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   @Autowired
   private UserRepository userRepository;
   
   public void saveUser(User user) {
       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       String encodedPassword = encoder.encode(user.getPassword());
       user.setPassword(encodedPassword);
       user.setEnabled(false);
       userRepository.saveUser(user);
   }

   public List<User> findAll() {
       return userRepository.findAll();
   }

   public User getUser(String username) {
       return userRepository.getUser(username);
   }
}
