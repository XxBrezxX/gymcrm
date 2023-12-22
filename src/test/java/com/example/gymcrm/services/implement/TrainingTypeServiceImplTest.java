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

import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.repositories.TrainingTypeDao;
import com.example.gymcrm.services.implementations.models.TrainingTypeServiceImpl;

@DataJpaTest
class TrainingTypeServiceImplTest {

    @Mock
    private TrainingTypeDao trainingTypeDao;

    @InjectMocks
    private TrainingTypeServiceImpl trServiceImpl;

    @Test
    void testCreateTrainingType() {
        TrainingType trainingType = new TrainingType();

        when(trainingTypeDao.save(trainingType)).thenReturn(trainingType);

        TrainingType response = trServiceImpl.createTrainingType(trainingType);

        assertEquals(trainingType, response);
    }

    @Test
    void testUpdateTrainingType() {
        TrainingType trainingType = new TrainingType();

        when(trainingTypeDao.save(trainingType)).thenReturn(trainingType);

        TrainingType response = trServiceImpl.updateTrainingType(trainingType);

        assertEquals(trainingType, response);
    }

    @Test
    void testGetAllTrainingTypes() {
        List<TrainingType> trainingTypes = new ArrayList<>();

        when(trainingTypeDao.findAll()).thenReturn(trainingTypes);

        List<TrainingType> response = trServiceImpl.getAllTrainingTypes();

        assertEquals(trainingTypes, response);
    }

    @Test
    void testDeleteTrainingType() {
        TrainingType trainingType = new TrainingType();
        trainingType.setId(1L);

        trServiceImpl.deleteTrainingType(trainingType.getId());

        verify(trainingTypeDao, atLeastOnce()).deleteById(1L);
    }

    @Test
    void testDeleteAll() {
        trServiceImpl.deleteAll();

        verify(trainingTypeDao, atLeastOnce()).deleteAll();
    }

    @Test
    void testGetTrainingTypeById() {
        TrainingType trainingType = new TrainingType();
        trainingType.setId(1L);

        when(trainingTypeDao.getReferenceById(1L)).thenReturn(trainingType);

        TrainingType response = trServiceImpl.getTrainingTypeById(trainingType.getId());

        assertEquals(trainingType, response);
    }
}
