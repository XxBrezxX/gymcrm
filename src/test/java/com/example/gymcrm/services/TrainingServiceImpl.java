package com.example.gymcrm.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.TrainingDao;
import com.example.gymcrm.services.implementations.models.TrainingServiceImpl;
import com.example.gymcrm.services.implementations.web.TrainerWorkloadService;

@DataJpaTest
class TrainingServiceImplTest {
    @Mock
    private TrainerWorkloadService trainerWorkloadService;

    @Mock
    private TrainingDao trainingDao;

    @InjectMocks
    private TrainingServiceImpl trainingServiceImpl;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTraining() {

        User user = new User(1L, "Name1", "Last1", "User1", "P1", true);
        Trainee trainee = new Trainee(1L, null, "TraineeAddress", user, null);
        Trainer trainer = new Trainer(1L, null, user, null);

        Training training = new Training();
        training.setId(1L);
        training.setDuration(1);
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName("name1");

        when(trainingDao.save(training)).thenReturn(training);

        Training response = trainingServiceImpl.createTraining(training);

        assertEquals(training, response);
    }

    @Test
    void testGetTrainingById() {
        Training training = new Training();

        when(trainingDao.getReferenceById(1L)).thenReturn(training);

        Training response = trainingServiceImpl.getTrainingById(1L);

        assertEquals(training, response);
    }

    @Test
    void testGetAllTrainings() {
        List<Training> trainings = new ArrayList<>();

        when(trainingDao.findAll()).thenReturn(trainings);

        List<Training> response = trainingServiceImpl.getAllTrainings();

        assertEquals(trainings, response);
    }

    @Test
    void testDeleteAll() {
        // Llamar al método deleteAll en el objeto trainingServiceImpl
        trainingServiceImpl.deleteAll();

        // Verificar que el método ha sido llamado una vez en trainingDao
        verify(trainingDao, Mockito.times(1)).deleteAll();
    }
}
