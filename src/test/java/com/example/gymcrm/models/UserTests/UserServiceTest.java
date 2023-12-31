package com.example.gymcrm.models.UserTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.UserDao;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@DataJpaTest
class UserServiceTest {

    private UserServiceImpl userServiceImpl;

    @Mock
    private UserDao userDao;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        MeterRegistry meterRegistry = new SimpleMeterRegistry();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userServiceImpl = new UserServiceImpl(meterRegistry, passwordEncoder);
        userServiceImpl.setUserDao(userDao);
    }

    private User generateTestUser() {
        User user = new User();
        user.setFirstName("TestUser");
        user.setIsActive(true);
        user.setLastName("Testlastname");
        user.setPassword("123");
        user.setUsername("username");

        return user;
    }

    @Test
    void testCreateUser_userCreated_returnUser() {
        User user = generateTestUser();

        when(userDao.save(user)).thenReturn(user);

        User result = userServiceImpl.createUser(user);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        verify(userDao, times(1)).save(user);
    }

    @Test
    void testUpdateUser_userUpdated_returnUser() {
        User user = generateTestUser();
        user.setId(1L);

        when(userDao.save(user)).thenReturn(user);

        User result = userServiceImpl.updateUser(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userDao, times(1)).save(user);
    }

    @Test
    void testDeleteUser_userExists_delete() {
        User user = generateTestUser();
        user.setId(1L);

        userServiceImpl.deleteUser(user.getId());

        verify(userDao, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserById_userExists_returnUser() {
        User user = generateTestUser();
        user.setId(1L);

        when(userDao.getReferenceById(1L)).thenReturn(user);

        User result = userServiceImpl.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userDao, times(1)).getReferenceById(1L);
    }

    @Test
    void testCreateUser_duplicates() {
        // Define a user object with duplicate first and last name
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setUsername(null);
        user1.setPassword(null);
        user1.setIsActive(true);

        // Define existing users with the same base username
        User existingUser1 = new User();
        existingUser1.setFirstName("John");
        existingUser1.setLastName("Doe");
        existingUser1.setUsername("John.Doe");
        existingUser1.setPassword("password");
        existingUser1.setIsActive(true);
        User existingUser2 = new User();
        existingUser2.setFirstName("John");
        existingUser2.setLastName("Doe");
        existingUser2.setUsername("John.Doe1");
        existingUser2.setPassword("password");
        existingUser2.setIsActive(true);
        // Return existing users in the DAO
        when(userDao.findByUsername("John.Doe")).thenReturn(existingUser1);
        when(userDao.findByUsername("John.Doe1")).thenReturn(existingUser2);
        when(userDao.save(user1)).thenReturn(user1);

        // Call the createUser method to add the user to the system
        User resultUser = userServiceImpl.createUser(user1);

        // Verify that the newUser has a unique username
        assertNotNull(resultUser.getUsername());
        assertEquals("John.Doe2", resultUser.getUsername());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();

        when(userDao.findAll()).thenReturn(users);

        List<User> response = userServiceImpl.getAllUsers();

        assertEquals(users, response);
    }

    @Test
    void testDeleteAll() {
        userServiceImpl.deleteAll();

        verify(userDao, Mockito.times(1)).deleteAll();
    }

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user");

        when(userDao.findByUsername(user.getUsername())).thenReturn(user);

        User response = userServiceImpl.findByUsername(user.getUsername());

        assertEquals(user.getId(), response.getId());
    }
}
