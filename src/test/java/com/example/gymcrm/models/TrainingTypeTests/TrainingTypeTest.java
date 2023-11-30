package com.example.gymcrm.models.TrainingTypeTests;

import com.example.gymcrm.model.TrainingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTypeTest {

    @Test
    void gettersAndSettersTest() {
        Long id = 1L;
        String trainingTypeName = "Pilates";

        TrainingType trainingType = new TrainingType();
        assertNull(trainingType.getId()); // comprobamos que por defecto el id es nulo
        assertNull(trainingType.getTrainingTypeName()); // comprobamos que por defecto el nombre es nulo

        trainingType.setId(id);
        assertEquals(id, trainingType.getId()); // comprobamos que se ha establecido correctamente el id

        trainingType.setTrainingTypeName(trainingTypeName);
        assertEquals(trainingTypeName, trainingType.getTrainingTypeName()); // comprobamos que se ha establecido
                                                                            // correctamente el nombre
    }

}