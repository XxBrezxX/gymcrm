package com.example.gymcrm.services.implementations.models;

import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.UserDao;
import com.example.gymcrm.services.pureServices.UserService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class UserServiceImpl implements UserService {

    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    private final Counter counter;

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHAR_POOL.length());
            char randomChar = CHAR_POOL.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public UserServiceImpl(MeterRegistry meterRegistry, PasswordEncoder passwordEncoder) {
        counter = Counter.builder("mi.contador")
                .description("Un contador personalizado")
                .register(meterRegistry);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        String baseUsername = user.getFirstName().concat(".").concat(user.getLastName());
        String newUsername = baseUsername;

        User existingUsers = userDao.findByUsername(newUsername);
        int i = 1;
        while (existingUsers != null) {
            newUsername = baseUsername.concat(String.valueOf(i));
            existingUsers = userDao.findByUsername(newUsername);
            i++;
        }

        // TO-DO: Borrar esta parte en un futuro para implementar seguridad al 100%
        String password = generateRandomPassword();
        LOGGER.info("################User:".concat(newUsername).concat(" password:").concat(password));
        user.setUsername(newUsername);
        user.setPassword(passwordEncoder.encode(password));

        // Uso de Prometheus para saber numero de creates hechos
        counter.increment();

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

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void setUserDao(UserDao userDao2) {
        this.userDao = userDao2;
    }

}
