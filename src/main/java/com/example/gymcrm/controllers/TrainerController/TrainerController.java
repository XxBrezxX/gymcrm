package com.example.gymcrm.controllers.TrainerController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

@Controller
@RequestMapping("/trainers")
public class TrainerController {

    @Autowired
    private TrainerServiceImpl trainerServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("trainer", new Trainer());
        return "controllers/trainer/registerTrainer";
    }

    @PostMapping("/register")
    public String registerTrainer(@ModelAttribute("user") User user, @ModelAttribute("trainer") Trainer trainer) {
        user.setIsActive(true);
        User persisted = userServiceImpl.createUser(user);
        trainer.setUser(persisted);
        trainerServiceImpl.createTrainer(trainer);
        return "redirect:/trainers/list";
    }

    @GetMapping("/list")
    public String getTrainers(Model model) {
        List<Trainer> trainers = trainerServiceImpl.getAllTrainers();
        model.addAttribute("trainers", trainers);
        return "controllers/trainer/listTrainer";
    }

    @GetMapping("/modify/{username}")
    public String getTrainer(@PathVariable String username, Model model) {
        Trainer trainer = trainerServiceImpl.getTrainerByUsername(username);
        model.addAttribute("trainer", trainer);
        return "controllers/trainer/modifyTrainer";
    }

    @PostMapping("/modify")
    public String newPassword(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpServletRequest request, HttpServletResponse response) {
        User user = userServiceImpl.findByUsername(username);
        userServiceImpl.updatePassword(password, user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}
