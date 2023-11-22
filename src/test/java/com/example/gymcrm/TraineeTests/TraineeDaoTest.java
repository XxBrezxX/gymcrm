package com.example.gymcrm.TraineeTests;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.gymcrm.repositories.TraineeDao;
import com.example.gymcrm.services.implementations.TraineeServiceImpl;

public class TraineeDaoTest {

    @Mock
    private TraineeDao traineeDao;

    @InjectMocks
    private TraineeServiceImpl traineeServiceImpl;

}
