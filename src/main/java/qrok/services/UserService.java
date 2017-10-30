package qrok.services;

import qrok.entitties.User;

public interface UserService {
	User add(User user);

	User findByUsername(String username);
}
