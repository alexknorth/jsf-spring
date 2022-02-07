package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.TransferDetails;
import de.northcodes.course.jsfspring.model.User;

public interface TransferService {

	TransferDetails transferNow(TransferDetails transferDetails);

	List<TransferDetails> getAllTransfersByUser(User transferredBy);

	List<TransferDetails> getOutgoingTransfers(User currentUser);

	List<TransferDetails> getIncomingTransfers(User currentUser);
}
