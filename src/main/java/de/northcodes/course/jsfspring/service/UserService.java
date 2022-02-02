package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.User;

public interface UserService {

    User getUser(int bankAccountNumber);

    void saveUser(User user);

	boolean isEmailAlreadyExisting(String emailAddress);
}
