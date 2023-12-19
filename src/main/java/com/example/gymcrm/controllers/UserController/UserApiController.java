package com.example.gymcrm.controllers.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gymcrm.model.User;
import com.example.gymcrm.model.requests.UpdatePaswordRequest;
import com.example.gymcrm.model.requests.UpdateStatusRequest;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/users/api")
public class UserApiController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/update")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePaswordRequest data) {
        User user = userServiceImpl.findByUsername(data.getUsername());

        if (!passwordEncoder.matches(data.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Old password is incorrect.");
        }

        userServiceImpl.updatePassword(data.getNewPassword(), user);

        return ResponseEntity.status(200).body("Password updated.");
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateActive(@RequestBody UpdateStatusRequest data) {
        User user = userServiceImpl.findByUsername(data.getUsername());
        user.setIsActive(data.getIsActive());
        user = userServiceImpl.updateUser(user);
        return ResponseEntity.ok("OK");
    }
}
