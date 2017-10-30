package qrok.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import qrok.entitties.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

}
