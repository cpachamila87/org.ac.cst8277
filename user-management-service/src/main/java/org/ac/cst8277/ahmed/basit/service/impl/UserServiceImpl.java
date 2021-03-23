package org.ac.cst8277.ahmed.basit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ac.cst8277.ahmed.basit.domain.Role;
import org.ac.cst8277.ahmed.basit.domain.User;
import org.ac.cst8277.ahmed.basit.domain.dao.UserDAO;
import org.ac.cst8277.ahmed.basit.domain.dto.UserDTO;
import org.ac.cst8277.ahmed.basit.repository.UserRepository;
import org.ac.cst8277.ahmed.basit.service.UserService;
import org.ac.cst8277.ahmed.basit.util.Converter;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_CODE;
import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUCCESS_CODE;
import static org.ac.cst8277.ahmed.basit.util.Constant.USER_SUCCESS_ADD_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.USER_SUCCESS_DELETE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.USER_SUCCESS_RETRIEVE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.USER_SUCCESS_UPDATE_MESSAGE;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BaseResponse getAllUsers() {
        BaseResponse response;
        List<UserDAO> userDAOS;

        try {
            List<User> userList = userRepository.findAll();
            userDAOS = userList.stream().map(Converter::convertToUserDAO).collect(Collectors.toList());
            response = new BaseResponse(SUCCESS_CODE, null, userDAOS, USER_SUCCESS_RETRIEVE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse getUserByField(long id) {
        BaseResponse response;
        UserDAO userDAO;

        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                userDAO = Converter.convertToUserDAO(userOptional.get());
                response = new BaseResponse(SUCCESS_CODE, userDAO, null, USER_SUCCESS_RETRIEVE_MESSAGE);
            } else {
                response = new BaseResponse(ERROR_CODE, null, null, ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, "");
        }
        return response;
    }

    @Override
    public BaseResponse getUserByUsername(String username) {
        BaseResponse response;
        UserDAO userDAO;

        try {
            Optional<User> userOptional = userRepository.findByUserName(username);
            if (userOptional.isPresent()) {
                userDAO = Converter.convertToUserDAO(userOptional.get());
                response = new BaseResponse(SUCCESS_CODE, userDAO, null, USER_SUCCESS_RETRIEVE_MESSAGE);
            } else {
                response = new BaseResponse(ERROR_CODE, null, null, ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse getUserByUserRole(String role) {
        BaseResponse response;
        List<UserDAO> userDAOS;

        try {
            List<User> users = userRepository.findByRolesRole(role);
            userDAOS = users.stream().map(Converter::convertToUserDAO).collect(Collectors.toList());
            response = new BaseResponse(SUCCESS_CODE, null, userDAOS, USER_SUCCESS_RETRIEVE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse createNewUser(UserDTO userDTO) {
        BaseResponse response;
        User user;
        UserDAO userDAO;
        Set<Role> roles;

        try {
            user = Converter.convertUserDTOToUser(userDTO);
            roles = userDTO.getUserRole().stream().map(Converter::convertToRole).collect(Collectors.toSet());
            user.setRoles(roles);
            user = userRepository.save(user);
            userDAO = Converter.convertToUserDAO(user);
            response = new BaseResponse(SUCCESS_CODE, userDAO, null, USER_SUCCESS_ADD_MESSAGE);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse updateUser(UserDTO userDTO) {
        BaseResponse response;
        UserDAO userDAO;

        try {
            Optional<User> userOptional = userRepository.findById(userDTO.getId());
            if (userOptional.isPresent()) {
                User user = Converter.convertUserDTOToUser(userDTO, userOptional.get());
                userDAO = Converter.convertToUserDAO(userRepository.save(user));
                response = new BaseResponse(SUCCESS_CODE, userDAO, null, USER_SUCCESS_UPDATE_MESSAGE);
            } else {
                response = new BaseResponse(ERROR_CODE, null, null, ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse deleteUser(long id) {
        BaseResponse response;

        try {
            userRepository.deleteById(id);
            response = new BaseResponse(SUCCESS_CODE, null, null, USER_SUCCESS_DELETE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }
}
