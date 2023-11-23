package com.example.gymcrm.UserTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.UserDao;

@DataJpaTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

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
    public void testSaveUser() {
        User user = generateTestUser();

        User savedUser = userDao.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getFirstName().contentEquals("TestUser"));
    }

    @Test
    public void testFindById() {
        User user = generateTestUser();
        userDao.save(user);

        Optional<User> foundUser = userDao.findById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(foundUser.get().getId(), user.getId());
    }

    @Test
    public void testFindAll() {
        User user1 = generateTestUser();
        User user2 = generateTestUser();
        User user3 = generateTestUser();

        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);

        List<User> usersFound = userDao.findAll();

        assertNotNull(usersFound);
        assertEquals(usersFound.size(), 3);
    }

    @Test
    public void testUpdateUser() {
        User user = generateTestUser();
        userDao.save(user);

        user.setFirstName("Updated Name");

        User updated = userDao.save(user);

        assertNotNull(updated);
        assertEquals(user, updated);
        assertTrue(user.getId() == updated.getId());
    }

    @Test
    public void testDeleteById() {
        User user = generateTestUser();
        userDao.save(user);

        userDao.deleteById(user.getId());

        Optional<User> deletedUser = userDao.findById(user.getId());

        assertFalse(deletedUser.isPresent());
    }
}
