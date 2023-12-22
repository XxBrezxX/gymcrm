package com.example.gymcrm.models.requests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.requests.UpdateStatusRequest;

@DataJpaTest
public class UpdateStatusRequestTest {

    @Test
    public void createRequestTest() {
        UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest("user", true);
        assertNotNull(updateStatusRequest);
        assertSame("user", updateStatusRequest.getUsername());
        assertTrue(updateStatusRequest.getIsActive());
    }

}
