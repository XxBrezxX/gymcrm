package com.example.gymcrm.controllers.TraineeController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

@Controller
@RequestMapping("/trainees")
public class TraineeController {

    @Autowired
    private TraineeServiceImpl traineeServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TrainingServiceImpl trainingServiceImpl;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("trainee", new Trainee());
        return "controllers/trainee/registerTrainee";
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@ModelAttribute("user") User user,
            @ModelAttribute("trainee") Trainee trainee) {
        user.setIsActive(true);
        User persisted = userServiceImpl.createUser(user);
        trainee.setUser(persisted);
        traineeServiceImpl.createTrainee(trainee);

        Map<String, String> response = new HashMap<>();
        response.put("username", persisted.getUsername());
        response.put("password", persisted.getPassword());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public String getTrainees(Model model) {
        List<Trainee> trainees = traineeServiceImpl.getAllTrainees();
        model.addAttribute("trainees", trainees);
        return "controllers/trainee/listTrainee";
    }

    @GetMapping("/modify/{username}")
    public String getTrainer(@PathVariable String username, Model model) {
        Trainee trainee = traineeServiceImpl.getTraineeByUsername(username);
        model.addAttribute("trainee", trainee);
        return "controllers/trainee/modifyTrainee";
    }

    @PutMapping("/modify")
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

    @PostMapping("/updateStatus/{username}")
    public String updateStatus(@PathVariable("username") String username) {
        User user = userServiceImpl.findByUsername(username);
        user.setIsActive(!user.getIsActive());
        userServiceImpl.updateUser(user);
        return "redirect:/trainees/list";
    }

    @PostMapping("/delete/{username}")
    public String deleteTrainee(@PathVariable("username") String username) {
        Trainee trainee = traineeServiceImpl.getTraineeByUsername(username);
        trainingServiceImpl.deleteTrainingsByTrainee(trainee);
        traineeServiceImpl.deleteTraineeByUsername(username);
        return "redirect:/trainees/list";
    }

    
}
