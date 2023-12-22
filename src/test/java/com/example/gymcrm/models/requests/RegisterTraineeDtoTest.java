package com.example.gymcrm.models.requests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.requests.RegisterTraineeDto;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.sql.Date;

@DataJpaTest
public class RegisterTraineeDtoTest {

    @Test
    public void createRequest_test() {
        Date date = Date.valueOf(LocalDate.of(2000, 1, 1));
        RegisterTraineeDto rDto = new RegisterTraineeDto("name", "last", date, "address");
        
        rDto.setDateOfBirth(date);
        rDto.setAddress("add2");

        assertNotNull(rDto);
        assertSame("add2", rDto.getAddress());
    }
}
