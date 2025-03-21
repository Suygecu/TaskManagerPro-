package com.kostya.taskmanager.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserUpdateDto {
    private String username;
    private String email;
    private String password;
    private String role;

}
