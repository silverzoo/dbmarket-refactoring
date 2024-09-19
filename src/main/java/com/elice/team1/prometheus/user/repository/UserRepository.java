package com.elice.team1.prometheus.user.repository;

import com.elice.team1.prometheus.user.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    // 로그인 로직
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
