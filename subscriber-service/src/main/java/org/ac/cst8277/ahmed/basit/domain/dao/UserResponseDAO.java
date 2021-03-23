package org.ac.cst8277.ahmed.basit.domain.dao;

import lombok.Data;
import org.ac.cst8277.ahmed.basit.util.BaseMessage;

@Data
public class UserResponseDAO extends BaseMessage {
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
