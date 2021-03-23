package org.ac.cst8277.ahmed.basit.service;

import org.ac.cst8277.ahmed.basit.domain.dto.RoleDTO;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;

public interface RoleService {
    BaseResponse getRoles();
    BaseResponse createNewRole(RoleDTO roleDTO);
    BaseResponse updateRole(RoleDTO roleDTO);
    BaseResponse deleteRole(long id);
}
