package com.elice.team1.prometheus.user.repository;

import com.elice.team1.prometheus.user.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    // 로그인 로직
    User findByUserNameAndPassword(String username, String password);
    User findByUserName(String userName);
    User findByUserId(Long userId);
}
