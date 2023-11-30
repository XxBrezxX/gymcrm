package com.example.gymcrm.services.implementations.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.TraineeDao;
import com.example.gymcrm.repositories.TrainerDao;
import com.example.gymcrm.repositories.UserDao;

import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
@Setter
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TraineeDao traineeDao;

    @Autowired
    private TrainerDao trainerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Verifica si el usuario es un Trainer o Trainee
        boolean isTrainer = trainerDao.findByUserId(user.getId()) != null;
        boolean isTrainee = traineeDao.findByUserId(user.getId()) != null;

        // Construye la instancia de UserDetails según los roles de Trainer y Trainee
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword());

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (isTrainer) {
            authorities.add(new SimpleGrantedAuthority("ROLE_TRAINER"));
        }

        if (isTrainee) {
            authorities.add(new SimpleGrantedAuthority("ROLE_TRAINEE"));
        }

        return userBuilder.authorities(authorities).build();
    }
}