package com.blog.blogappapi.payloads;

import com.blog.blogappapi.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min=4, message = "username must be min of 4 characters")
    private String name;

    @Email(message = "email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "password must be min of 3 chars and max of 10 chars!!")

  //@Pattern(regexp = )
    @JsonIgnore
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles=new HashSet<>();
}
