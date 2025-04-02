package com.myUber.myUber.DTO;

import com.myUber.myUber.Entities.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private Long id;

    private String name;
        private String email;
                private String password;
    private Set<Role> roles;

}
