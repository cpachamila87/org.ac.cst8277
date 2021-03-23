package org.ac.cst8277.ahmed.basit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ac.cst8277.ahmed.basit.domain.Role;
import org.ac.cst8277.ahmed.basit.domain.dao.RoleDAO;
import org.ac.cst8277.ahmed.basit.domain.dto.RoleDTO;
import org.ac.cst8277.ahmed.basit.repository.RoleRepository;
import org.ac.cst8277.ahmed.basit.service.RoleService;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_CODE;
import static org.ac.cst8277.ahmed.basit.util.Constant.ERROR_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.ROLE_SUCCESS_ADD_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.ROLE_SUCCESS_DELETE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.ROLE_SUCCESS_RETRIEVE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.ROLE_SUCCESS_UPDATE_MESSAGE;
import static org.ac.cst8277.ahmed.basit.util.Constant.SUCCESS_CODE;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public BaseResponse getRoles() {
        BaseResponse response;
        List<RoleDAO> roleDAOS;

        try {
            List<Role> roleList = roleRepository.findAll();
            roleDAOS = roleList.stream().map(role -> {
                RoleDAO roleDAO = new RoleDAO();
                roleDAO.setId(role.getId());
                roleDAO.setRole(role.getRole());
                return roleDAO;
            }).collect(Collectors.toList());

            response = new BaseResponse(SUCCESS_CODE, null, roleDAOS, ROLE_SUCCESS_RETRIEVE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }


    @Override
    public BaseResponse createNewRole(RoleDTO roleDTO) {
        BaseResponse response;
        RoleDAO roleDAO;
        Role role;

        try {
            role = roleRepository.save(new Role(roleDTO.getRole()));
            roleDAO = new RoleDAO(role.getId(), role.getRole());
            response = new BaseResponse(SUCCESS_CODE, roleDAO, null, ROLE_SUCCESS_ADD_MESSAGE);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(ERROR_CODE, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse updateRole(RoleDTO roleDTO) {
        BaseResponse response;
        RoleDAO roleDAO;
        Role role;

        try {
            Optional<Role> roleOptional = roleRepository.findById(roleDTO.getId());
            if (roleOptional.isPresent()) {
                role = roleOptional.get();
                role.setRole(roleDTO.getRole());
                roleRepository.save(role);
                roleDAO = new RoleDAO(role.getId(), role.getRole());
                response = new BaseResponse(SUCCESS_CODE, roleDAO, null, ROLE_SUCCESS_UPDATE_MESSAGE);
            } else {
                response = new BaseResponse(ERROR_CODE, null, null, ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(500, null, null, ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse deleteRole(long id) {
        BaseResponse response;

        try {
            roleRepository.deleteById(id);
            response = new BaseResponse(200, null, null, ROLE_SUCCESS_DELETE_MESSAGE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponse(500, null, null, ex.getMessage());
        }
        return response;
    }
}
