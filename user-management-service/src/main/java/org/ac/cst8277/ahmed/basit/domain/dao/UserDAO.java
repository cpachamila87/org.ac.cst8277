package org.ac.cst8277.ahmed.basit.domain.dao;

import lombok.Data;
import org.ac.cst8277.ahmed.basit.util.BaseMessage;

import java.util.Set;

@Data
public class UserDAO extends BaseMessage {
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String country;
    private String email;
    private Set<RoleDAO> userRole;
}
