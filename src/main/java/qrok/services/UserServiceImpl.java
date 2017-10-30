package qrok.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import qrok.entitties.Role;
import qrok.entitties.User;
import qrok.repositories.RoleRepository;
import qrok.repositories.UserRepository;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public User add(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByName("USER"));
        user.setRoles(roles);
		return userRepo.saveAndFlush(user); 
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findUserByUsername(username);
	}
	
}
