package com.api.restful.apirestfulspringbootjava.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDTO {
    @NotBlank
    @Size(min=2,max = 100)
    

    @NotBlank
    @Size(min=8,max = 60)
    private String password;

    public String getUsername() {
        return null;
    }
    
}
