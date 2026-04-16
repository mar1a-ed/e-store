package com.mar1a_ed.e_store.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdatePasswordDto {

    @NotBlank
    @Size(min = 8, max = 30, message = "The password must contain at least 8 characters")
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 30, message = "The password must contain at least 8 characters")
    private String newPassword;

    @NotBlank
    @Size(min = 8, max = 30, message = "The password must contain at least 8 characters")
    private String confirmPassword;
}
