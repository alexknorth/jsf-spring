package de.northcodes.course.jsfspring.persistence;

import java.util.List;

import de.northcodes.course.jsfspring.model.TransferDetails;
import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.User;

public interface TransferRepository extends CrudRepository<TransferDetails, Long> {

	List<TransferDetails> findByTransferredBy(User transferredBy);
	List<TransferDetails> findByTransferredTo(User transferredTo);
}