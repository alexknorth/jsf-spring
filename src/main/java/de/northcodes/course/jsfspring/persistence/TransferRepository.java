package de.northcodes.course.jsfspring.persistence;

import java.util.List;

import de.northcodes.course.jsfspring.model.TransferDetails;
import de.northcodes.course.jsfspring.model.User;
import org.springframework.data.repository.CrudRepository;

public interface TransferRepository extends CrudRepository<TransferDetails, Long> {

    List<TransferDetails> findByTransferredBy(User transferredBy);

    List<TransferDetails> findByTransferredTo(User transferredTo);
}