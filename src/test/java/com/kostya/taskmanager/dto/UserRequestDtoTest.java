package com.kostya.taskmanager.dto;

import com.kostya.taskmanager.dto.userdto.UserRequestDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRequestDtoTest {

    @Test
    public void testDto() {
        UserRequestDto dto = new UserRequestDto();
        dto.setEmail("test@example.com");
        assertEquals("test@example.com", dto.getEmail());
    }
}