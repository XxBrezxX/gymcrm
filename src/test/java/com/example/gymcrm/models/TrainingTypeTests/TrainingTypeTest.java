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

    @Test
    void testToString() {
        Long id = 1L;
        String trainingTypeName = "Pilates";

        TrainingType trainingType = new TrainingType(id, trainingTypeName);
        String expected = "TrainingType(id=1, trainingTypeName=Pilates)";
        assertEquals(expected, trainingType.toString()); // comprobamos que el método toString() devuelve la cadena
                                                         // esperada
    }

    @Test
    void testEqualsAndHashCode() {
        Long id1 = 1L;
        String trainingTypeName1 = "Pilates";

        Long id2 = 2L;
        String trainingTypeName2 = "Yoga";

        TrainingType trainingType1 = new TrainingType(id1, trainingTypeName1);
        TrainingType trainingType2 = new TrainingType(id2, trainingTypeName2);
        TrainingType trainingType3 = new TrainingType(id1, trainingTypeName1);

        assertEquals(trainingType1, trainingType3); // comprobamos que dos objetos con los mismos campos son iguales
        assertNotEquals(trainingType1, trainingType2); // comprobamos que dos objetos con campos diferentes no son
                                                       // iguales
        assertNotEquals(null, trainingType1); // comprobamos que un objeto no es igual a null
        assertNotEquals(trainingType1, new Object()); // comprobamos que un objeto no es igual a otro objeto de otra
                                                      // clase
        assertEquals(trainingType1.hashCode(), trainingType3.hashCode()); // comprobamos que dos objetos iguales tienen
                                                                          // el mismo hashCode()
    }
}