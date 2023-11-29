package com.example.gymcrm.UserTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class UserServiceTest {

    private UserServiceImpl userServiceImpl;

    @Mock
    private UserDao userDao;

    @BeforeEach
    public void init() {
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
    public void testCreateUser_userCreated_returnUser() {
        // prepare user object to be created
        User user = generateTestUser();

        // configure the userDao to return this same user object when saving the user
        when(userDao.save(user)).thenReturn(user);

        // call the method to create the user object
        User result = userServiceImpl.createUser(user);

        // verify that the result is not null and contains the expected values
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        // add other assertions for the other properties of the user object

        // verify that the userDao.save method was called exactly once with the expected
        // user object
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void testUpdateUser_userUpdated_returnUser() {
        User user = generateTestUser();
        user.setId(1L);

        when(userDao.save(user)).thenReturn(user);

        User result = userServiceImpl.updateUser(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void testDeleteUser_userExists_delete() {
        User user = generateTestUser();
        user.setId(1L);

        userServiceImpl.deleteUser(user.getId());

        verify(userDao, times(1)).deleteById(1L);
    }

    @Test
    public void testGetUserById_userExists_returnUser() {
        User user = generateTestUser();
        user.setId(1L);

        when(userDao.getReferenceById(1L)).thenReturn(user);

        User result = userServiceImpl.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userDao, times(1)).getReferenceById(1L);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = generateTestUser();
        User user2 = generateTestUser();
        User user3 = generateTestUser();
        user1.setId(1L);
        user2.setId(2L);
        user3.setId(3L);

        when(userDao.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userDao.findAll();

        assertEquals(2, result.size());
        verify(userDao, times(1)).findAll();
    }
}
