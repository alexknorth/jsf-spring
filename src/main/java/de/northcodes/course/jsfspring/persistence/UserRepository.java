package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByBankAccountNumber(int bankAccountNumber);
    User findByEmailAddress(String emailAddress);
}