package de.northcodes.course.jsfspring;

public interface UserService {

    User getUser(String username);

    void saveUser(User user);
}
