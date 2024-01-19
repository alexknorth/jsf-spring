package de.northcodes.course.jsfspring.controller;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

}