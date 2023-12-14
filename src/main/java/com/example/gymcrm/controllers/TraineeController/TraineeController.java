package com.example.gymcrm.controllers.TraineeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

@Controller
@RequestMapping("/trainees")
public class TraineeController {

    @Autowired
    private TraineeServiceImpl traineeServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("trainee", new Trainee());
        return "controllers/trainee/registerTrainee";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, @ModelAttribute("trainee") Trainee trainee) {
        user.setIsActive(true);
        User persisted = userServiceImpl.createUser(user);
        trainee.setUser(persisted);
        traineeServiceImpl.createTrainee(trainee);
        return "redirect:/h2-console";
    }
}
