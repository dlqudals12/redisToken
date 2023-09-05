package com.practiceProject.repository;

import com.practiceProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLoginId(String loginId);

    User findUserByIdx(Long idx);
}
