package com.codecool.kulcssoft_app.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    private String userName;

    @Column(unique=true)
    @NotEmpty
    @Email
    private String userEmail;

    @NotNull
    @Email
    private String adminEmail;
}
