package org.ac.cst8277.ahmed.basit.repository;

import org.ac.cst8277.ahmed.basit.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
