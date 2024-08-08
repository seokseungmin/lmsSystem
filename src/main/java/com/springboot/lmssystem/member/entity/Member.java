package com.springboot.lmssystem.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Member {

    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;

    private LocalDateTime registerDate;
    private LocalDateTime emailAuthDt;

    private boolean emailAuthYn;
    private String emailAuthKey;

}
