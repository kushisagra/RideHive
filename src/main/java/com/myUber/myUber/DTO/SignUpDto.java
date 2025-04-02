package com.myUber.myUber.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SignUpDto {

    private String name;
    private String email;
    private String password;

}
