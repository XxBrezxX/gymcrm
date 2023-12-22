package com.example.gymcrm.services.implement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.TrainerDao;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

@DataJpaTest
class TrainerServiceImplTest {

    @Mock
    private TrainerDao trainerDao;

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private TrainerServiceImpl trainerServiceImpl;

    @Test
    void testCreateTrainer() {
        User user = new User();
        user.setId(1L);

        when(userServiceImpl.createUser(user)).thenReturn(user);

        Trainer trainer = new Trainer();
        trainer.setId(1L);

        when(trainerDao.save(trainer)).thenReturn(trainer);

        trainer.setUser(user);
        Trainer response = trainerServiceImpl.createTrainer(trainer);

        assertEquals(trainer, response);
    }

    @Test
    void testUpdateTrainer() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);

        when(trainerDao.save(trainer)).thenReturn(trainer);

        Trainer response = trainerServiceImpl.updateTrainer(trainer);

        assertEquals(trainer, response);
    }

    @Test
    void testGetTrainerById() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);

        when(trainerDao.getReferenceById(1L)).thenReturn(trainer);

        Trainer response = trainerServiceImpl.getTrainerById(trainer.getId());

        verify(trainerDao, atLeastOnce()).getReferenceById(1L);
        assertEquals(trainer, response);
    }

    @Test
    void testDeleteAll_Relations() {
        trainerServiceImpl.deleteAll();
        trainerServiceImpl.deleteAllTraineeTrainerRelations();

        verify(trainerDao, atLeastOnce()).deleteAll();
        verify(trainerDao, atLeastOnce()).deleteAllTraineeTrainerRelations();
    }

    @Test
    void testGetAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();

        when(trainerDao.findAll()).thenReturn(trainers);

        List<Trainer> response = trainerServiceImpl.getAllTrainers();

        verify(trainerDao, atLeastOnce()).findAll();
        assertEquals(trainers, response);
    }
}
