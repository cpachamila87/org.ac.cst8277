package org.ac.cst8277.ahmed.basit.service;

import org.ac.cst8277.ahmed.basit.domain.dto.UserDTO;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;

public interface UserService {
    BaseResponse getAllUsers();
    BaseResponse getUserByField(long id);
    BaseResponse getUserByUsername(String username);
    BaseResponse getUserByUserRole(String role);
    BaseResponse createNewUser(UserDTO customerDTO);
    BaseResponse updateUser(UserDTO userDTO);
    BaseResponse deleteUser(long id);
}
