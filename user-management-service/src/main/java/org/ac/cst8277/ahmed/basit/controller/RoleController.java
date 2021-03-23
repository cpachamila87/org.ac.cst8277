package org.ac.cst8277.ahmed.basit.controller;

import org.ac.cst8277.ahmed.basit.domain.dto.RoleDTO;
import org.ac.cst8277.ahmed.basit.service.RoleService;
import org.ac.cst8277.ahmed.basit.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.accepted().body(roleService.getRoles());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> addRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.accepted().body(roleService.createNewRole(roleDTO));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.accepted().body(roleService.updateRole(roleDTO));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<BaseResponse> deleteRole(@PathVariable("roleId") int roleId) {
        return ResponseEntity.accepted().body(roleService.deleteRole(roleId));
    }

}
