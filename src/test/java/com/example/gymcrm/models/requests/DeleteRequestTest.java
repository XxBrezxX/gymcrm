package com.example.gymcrm.models.requests;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.requests.DeleteRequest;

@DataJpaTest
public class DeleteRequestTest {

    @Test
    public void createRequest() {
        DeleteRequest deleteRequest = new DeleteRequest("user");
        assertNotNull(deleteRequest);
    }
}
