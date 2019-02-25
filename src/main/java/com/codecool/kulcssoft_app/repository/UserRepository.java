package com.codecool.kulcssoft_app.repository;

import com.codecool.kulcssoft_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUSerById(Integer id);

    void deleteUserById(Integer id);
}
