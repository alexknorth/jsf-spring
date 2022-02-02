package de.northcodes.course.jsfspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(int bankAccountNumber) {
        return userRepository.findByBankAccountNumber(bankAccountNumber);
    }

    @Override
    public void saveUser(User user) {
    	userRepository.save(user);
    }

	@Override
	public boolean isEmailAlreadyExisting(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress) != null;
	}
}
