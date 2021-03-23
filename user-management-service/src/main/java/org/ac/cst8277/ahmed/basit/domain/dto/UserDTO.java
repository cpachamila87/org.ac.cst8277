package org.ac.cst8277.ahmed.basit.domain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String country;
    private String email;
    private String password;
    private Set<RoleDTO> userRole;
}
