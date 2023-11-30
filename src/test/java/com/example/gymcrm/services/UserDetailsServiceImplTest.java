package com.example.gymcrm.services;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.TraineeDao;
import com.example.gymcrm.repositories.TrainerDao;
import com.example.gymcrm.repositories.UserDao;
import com.example.gymcrm.services.implementations.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@DataJpaTest
public class UserDetailsServiceImplTest {

    private UserDao userDaoMock;
    private TraineeDao traineeDaoMock;
    private TrainerDao trainerDaoMock;
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        userDaoMock = mock(UserDao.class);
        traineeDaoMock = mock(TraineeDao.class);
        trainerDaoMock = mock(TrainerDao.class);

        userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setUserDao(userDaoMock);
        userDetailsService.setTraineeDao(traineeDaoMock);
        userDetailsService.setTrainerDao(trainerDaoMock);
    }

    @Test
    void loadUserByUsername_WhenUserExistsAndIsTrainer_ShouldReturnUserDetailsWithRoleTrainer() {
        String username = "johndoe";
        String password = "password";
        long userId = 1L;

        when(userDaoMock.findByUsername(username))
                .thenReturn(new User(userId, "name", "last", username, password, false));
        when(trainerDaoMock.findByUserId(userId)).thenReturn(new Trainer());

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertEquals(userDetails.getUsername(), username);

        Set<SimpleGrantedAuthority> authoritiesSet = (Set<SimpleGrantedAuthority>) userDetails.getAuthorities();
        List<String> authorities = new ArrayList<>();
        for (SimpleGrantedAuthority authority : authoritiesSet) {
            authorities.add(authority.toString());
        }

        // Se compara usando la lista de roles
        assertEquals(Arrays.asList("ROLE_TRAINER"), authorities);
    }

    @Test
    void loadUserByUsername_WhenUserExistsAndIsTrainee_ShouldReturnUserDetailsWithRoleTrainee() {
        String username = "johndoe";
        String password = "password";
        long userId = 1L;

        when(userDaoMock.findByUsername(username))
                .thenReturn(new User(userId, "name", "last", username, password, false));
        when(traineeDaoMock.findByUserId(userId)).thenReturn(new Trainee());

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertEquals(userDetails.getUsername(), username);

        Set<SimpleGrantedAuthority> authoritiesSet = (Set<SimpleGrantedAuthority>) userDetails.getAuthorities();
        List<String> authorities = new ArrayList<>();
        for (SimpleGrantedAuthority authority : authoritiesSet) {
            authorities.add(authority.toString());
        }

        // Se compara usando la lista de roles
        assertEquals(Arrays.asList("ROLE_TRAINEE"), authorities);
    }

    @Test
    void loadUserByUsername_WhenUserDoesNotExist_ShouldThrowUsernameNotFoundException() {
        String username = "johndoe";

        when(userDaoMock.findByUsername(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }
}
