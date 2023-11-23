package com.example.gymcrm.services.implementations;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.UserDao;
import com.example.gymcrm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHAR_POOL.length());
            char randomChar = CHAR_POOL.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        String baseUsername = user.getFirstName().concat(".").concat(user.getLastName());
        String newUsername = baseUsername;

        List<User> existingUsers = userDao.findByUsername(newUsername);
        int i = 1;
        while (!existingUsers.isEmpty()) {
            newUsername = baseUsername.concat(String.valueOf(i));
            existingUsers = userDao.findByUsername(newUsername);
            i++;
        }

        user.setUsername(newUsername);
        user.setPassword(generateRandomPassword());
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getReferenceById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    public List<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
