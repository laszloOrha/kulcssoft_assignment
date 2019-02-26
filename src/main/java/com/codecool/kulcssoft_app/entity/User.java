package com.codecool.kulcssoft_app.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String userEmail;
    private String adminEmail;
}
