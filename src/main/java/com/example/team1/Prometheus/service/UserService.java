package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.entity.UserForm;
import com.example.team1.Prometheus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserForm form) {
        User user = form.toEntity();
        return userRepository.save(user);
    }

}
