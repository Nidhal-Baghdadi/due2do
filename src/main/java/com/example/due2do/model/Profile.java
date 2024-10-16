package com.example.due2do.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String googleAccessToken;

}

