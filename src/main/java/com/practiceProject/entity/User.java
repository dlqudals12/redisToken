package com.practiceProject.entity;

import com.practiceProject.dto.request.user.SaveUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Permission;

@Entity
@Table
@NoArgsConstructor
@Getter
@ToString
public class User extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long idx;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    public User(SaveUser saveUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.loginId = saveUser.getLoginId();
        this.password = encoder.encode(saveUser.getPassword());
        this.name = saveUser.getName();
    }
}
