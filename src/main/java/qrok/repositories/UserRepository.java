package qrok.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import qrok.entitties.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findUserByUsername (String username);
}
