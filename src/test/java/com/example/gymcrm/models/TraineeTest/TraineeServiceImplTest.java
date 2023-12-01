package com.example.gymcrm.models.TraineeTest;

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

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.TraineeDao;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

@DataJpaTest
class TraineeServiceImplTest {

    @Mock
    private TraineeDao traineeDao;

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private TraineeServiceImpl traineeServiceImpl;

    @Test
    void testCreateTrainee() {
        User user = new User();
        user.setId(1L);
        Trainee trainee = new Trainee();

        when(traineeDao.save(trainee)).thenReturn(trainee);

        Trainee response = traineeServiceImpl.createTrainee(trainee, user);

        assertEquals(trainee, response);
    }

    @Test
    void testUpdateTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);

        when(traineeDao.save(trainee)).thenReturn(trainee);

        Trainee response = traineeServiceImpl.updateTrainee(trainee);

        assertEquals(trainee.getId(), response.getId());
        assertEquals(trainee, response);
    }

    @Test
    void testDeleteTrainee() {
        traineeServiceImpl.deleteTrainee(1L);

        verify(traineeDao, atLeastOnce()).deleteById(1L);
    }

    @Test
    void testGetTraineeById() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);

        when(traineeDao.getReferenceById(1L)).thenReturn(trainee);

        Trainee response = traineeServiceImpl.getTraineeById(1L);

        assertEquals(trainee.getId(), response.getId());
        assertEquals(trainee, response);
    }

    @Test
    void testGetAllTrainees() {
        List<Trainee> trainees = new ArrayList<>();

        when(traineeDao.findAll()).thenReturn(trainees);

        List<Trainee> response = traineeServiceImpl.getAllTrainees();

        assertEquals(trainees, response);
    }

    @Test
    void testDeleteAll() {
        traineeServiceImpl.deleteAll();

        verify(traineeDao, atLeastOnce()).deleteAll();
    }
}
