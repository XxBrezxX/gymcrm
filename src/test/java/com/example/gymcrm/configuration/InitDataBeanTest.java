package com.example.gymcrm.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingTypeServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class InitDataBeanTest {

    // Mocks necesarios
    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private TrainingTypeServiceImpl trainingTypeServiceImpl;

    @MockBean
    private TraineeServiceImpl traineeServiceImpl;

    @MockBean
    private TrainerServiceImpl trainerServiceImpl;

    @MockBean
    private TrainingServiceImpl trainingServiceImpl;

    // Clase a probar
    private InitDataBean initDataBean = new InitDataBean();

    @Test
    public void testInitData() {

        // Ejecuta initData() manualmente
        initDataBean.initData(userServiceImpl, trainingTypeServiceImpl, traineeServiceImpl, trainerServiceImpl,
                trainingServiceImpl);

        // Verifica que los datos se hayan generado correctamente
        assertNotNull(userServiceImpl.getAllUsers());
        assertNotNull(trainingTypeServiceImpl.getAllTrainingTypes());
        assertNotNull(traineeServiceImpl.getAllTrainees());
        assertNotNull(trainerServiceImpl.getAllTrainers());
        assertNotNull(trainingServiceImpl.getAllTrainings());
    }
}