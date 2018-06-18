package enis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enis.backend.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
