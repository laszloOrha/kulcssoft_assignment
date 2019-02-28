package com.codecool.kulcssoft_app.repository;

import com.codecool.kulcssoft_app.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void saveUserWithAllData() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("test@useremail.com")
                .adminEmail("test@adminemail.com")
                .build();
        userRepository.save(user);

        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(1);
        entityManager.clear();
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveUserWithOutNameThrowsException() {
        User user = User.builder()
                .userEmail("test@useremail.com")
                .adminEmail("test@adminemail.com")
                .build();
        userRepository.saveAndFlush(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveUserWithOutUserEmailThrowsException() {
        User user = User.builder()
                .userName("Test Name")
                .adminEmail("test@adminemail.com")
                .build();
        userRepository.saveAndFlush(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveUserWithOutAdminEmailThrowsException() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("test@useremail.com")
                .build();
        userRepository.saveAndFlush(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveUserWithInvalidUserEmailThrowsException() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("testuseremail.com")
                .adminEmail("test@adminemail.com")
                .build();
        userRepository.saveAndFlush(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveUserWithInvalidAdminEmailThrowsException() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("test@useremail.com")
                .adminEmail("testadminemail.com")
                .build();
        userRepository.saveAndFlush(user);
    }

    @Test
    public void listUsersByCorrectAdminEmail() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("test@useremail.com")
                .adminEmail("test@adminemail.com")
                .build();
        userRepository.save(user);

        List<User> userListWithOneUser = userRepository.findAllByAdminEmail("test@adminemail.com");
        entityManager.clear();
        assertThat(userListWithOneUser).hasSize(1);
    }

    @Test
    public void listUsersByIncorrectAdminEmail() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("test@useremail.com")
                .adminEmail("test@adminemail.com")
                .build();
        userRepository.save(user);

        List<User> userListWithOneUser = userRepository.findAllByAdminEmail("invalidAddress@adminemail.com");
        entityManager.clear();
        assertThat(userListWithOneUser).hasSize(0);
    }

    @Test
    public void listUsersByEmptyAdminEmailString() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("test@useremail.com")
                .adminEmail("test@adminemail.com")
                .build();
        userRepository.save(user);

        List<User> userListWithOneUser = userRepository.findAllByAdminEmail("");
        entityManager.clear();
        assertThat(userListWithOneUser).hasSize(0);
    }

    @Test
    public void deleteExistingUserById() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("test@useremail.com")
                .adminEmail("test@adminemail.com")
                .build();


        userRepository.save(user);
        List<User> userListWithOneUser = userRepository.findAllByAdminEmail("test@adminemail.com");
        assertThat(userListWithOneUser).as("Addition error").hasSize(1);

        int userId = userListWithOneUser.get(0).getId();

        userRepository.deleteUserById(userId);
        userListWithOneUser = userRepository.findAllByAdminEmail("test@adminemail.com");
        assertThat(userListWithOneUser).as("Deletion error").hasSize(0);
    }

    @Test
    public void deleteNotExistingUserById() {
        User user = User.builder()
                .userName("Test Name")
                .userEmail("test@useremail.com")
                .adminEmail("test@adminemail.com")
                .build();

        userRepository.save(user);
        List<User> userListWithOneUser = userRepository.findAllByAdminEmail("test@adminemail.com");
        assertThat(userListWithOneUser).as("Addition error").hasSize(1);

        userRepository.deleteUserById(3);
        userListWithOneUser = userRepository.findAllByAdminEmail("test@adminemail.com");
        entityManager.clear();
        assertThat(userListWithOneUser).as("Deletion error").hasSize(1);
    }
}