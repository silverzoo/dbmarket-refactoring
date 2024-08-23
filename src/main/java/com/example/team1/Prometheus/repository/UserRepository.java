package com.example.team1.Prometheus.repository;

import com.example.team1.Prometheus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    // 로그인 로직
    User findByUserNameAndPassword(String username, String password);
    User findByUserName(String userName);
    User findByUserId(Long userId);
}
