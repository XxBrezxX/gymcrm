package com.example.gymcrm.models.TrainerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.model.User;

@DataJpaTest
class TrainerTest {

    private User generateTestUser(String name) {
        User user = new User();
        user.setFirstName(name);
        user.setIsActive(true);
        user.setLastName("Testlastname");
        user.setPassword("123");
        user.setUsername("username");

        return user;
    }

    @Test
    void testAllArgsConstructor() {
        User user1 = generateTestUser("User1");
        User user2 = generateTestUser("User2");
        Trainee trainee = new Trainee();
        trainee.setUser(user2);

        Set<Trainee> trainees = new HashSet<>();
        trainees.add(trainee);

        TrainingType tt = new TrainingType(1L, "Easy");

        Trainer trainer = new Trainer(1L, tt, user1, trainees);

        assertSame(1L, trainer.getId());
        assertEquals(trainees, trainer.getTrainees());
        assertEquals(user1, trainer.getUser());
        assertEquals(tt, trainer.getTrainingType());
    }

    @Test
    void testNoArgsConstructor() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setTrainees(null);
        trainer.setTrainingType(null);
        trainer.setUser(null);

        assertSame(1L, trainer.getId());
        assertNull(trainer.getTrainees());
        assertNull(trainer.getTrainingType());
        assertNull(trainer.getUser());
    }

}
