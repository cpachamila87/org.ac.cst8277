package org.ac.cst8277.ahmed.basit.util;

import org.ac.cst8277.ahmed.basit.domain.Role;
import org.ac.cst8277.ahmed.basit.domain.User;
import org.ac.cst8277.ahmed.basit.domain.dao.RoleDAO;
import org.ac.cst8277.ahmed.basit.domain.dao.UserDAO;
import org.ac.cst8277.ahmed.basit.domain.dto.RoleDTO;
import org.ac.cst8277.ahmed.basit.domain.dto.UserDTO;

import java.util.Set;
import java.util.stream.Collectors;


public class Converter {

    public static User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setProvince(userDTO.getProvince());
        user.setPassword(userDTO.getPassword());
        user.setCountry(userDTO.getCountry());
        return user;
    }

    public static UserDAO convertToUserDAO(User user){
        UserDAO userDAO = new UserDAO();
        userDAO.setId(user.getId());
        userDAO.setFirstName(user.getFirstName());
        userDAO.setLastName(user.getLastName());
        userDAO.setUserName(user.getUserName());
        userDAO.setEmail(user.getEmail());
        userDAO.setAddress(user.getAddress());
        userDAO.setCity(user.getCity());
        userDAO.setProvince(user.getProvince());
        userDAO.setCountry(user.getCountry());
        userDAO.setUserRole(Converter.convertToRoleDAO(user.getRoles()));
        return userDAO;
    }

    public static User convertUserDTOToUser(UserDTO userDTO, User user) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setProvince(userDTO.getProvince());
        user.setCountry(userDTO.getCountry());
        return user;
    }

    public static Role convertToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRole(roleDTO.getRole());
        return role;
    }

    public static Set<RoleDAO> convertToRoleDAO(Set<Role> roles) {
        return roles.stream().map(Converter::convertToRoleDAO).collect(Collectors.toSet());
    }

    public static RoleDAO convertToRoleDAO(Role role) {
        RoleDAO roleDAO = new RoleDAO();
        roleDAO.setId(role.getId());
        roleDAO.setRole(role.getRole());
        return roleDAO;
    }

}
