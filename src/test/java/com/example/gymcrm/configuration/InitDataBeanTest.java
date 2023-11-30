package com.example.gymcrm.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingTypeServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

@SpringBootTest
public class InitDataBeanTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private TrainingTypeServiceImpl trainingTypeServiceImpl;

    @Mock
    private TraineeServiceImpl traineeServiceImpl;

    @Mock
    private TrainerServiceImpl trainerServiceImpl;

    @Mock
    private TrainingServiceImpl trainingServiceImpl;

    @Mock
    private InitDataBean initDataBean;

    @Test
    public void testInitData() {
        MockitoAnnotations.openMocks(this);

        initDataBean.initData(userServiceImpl, trainingTypeServiceImpl, traineeServiceImpl, trainerServiceImpl,
                trainingServiceImpl);
        assertNotNull(userServiceImpl.getAllUsers());
        assertNotNull(trainingTypeServiceImpl.getAllTrainingTypes());
        assertNotNull(traineeServiceImpl.getAllTrainees());
        assertNotNull(trainerServiceImpl.getAllTrainers());
        assertNotNull(trainingServiceImpl.getAllTrainings());
    }
}
