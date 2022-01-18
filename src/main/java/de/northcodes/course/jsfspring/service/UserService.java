package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.User;

public interface UserService {

    User getUser(String username);

    void saveUser(User user);

	boolean isEmailAlreadyExisting(String emailAddress);
}
