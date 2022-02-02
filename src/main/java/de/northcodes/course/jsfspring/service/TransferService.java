package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.TransferDetails;
import de.northcodes.course.jsfspring.model.User;

public interface TransferService {

	public TransferDetails transferNow(TransferDetails transferDetails);

	public List<TransferDetails> getAllTransfersByUser(User transferredBy);
}
