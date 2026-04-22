package com.uep.wap.service;

import com.uep.wap.model.User;
import com.uep.wap.repository.UserRepository;
import com.uep.wap.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setActive(userDTO.getActive());
        userRepository.save(user);
        System.out.println("User added");
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}