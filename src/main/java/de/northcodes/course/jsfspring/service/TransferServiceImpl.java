package de.northcodes.course.jsfspring.service;

import java.util.List;

import javax.transaction.Transactional;

import de.northcodes.course.jsfspring.model.TransferDetails;
import de.northcodes.course.jsfspring.model.TransferState;
import de.northcodes.course.jsfspring.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.persistence.TransferRepository;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	TransferRepository transferRepository;

	@Autowired
	UserRepository userRepository;

	@Transactional
	@Override
	public TransferDetails transferNow(TransferDetails transferDetails) {
		User sender = transferDetails.getTransferredBy();
		User receiver = transferDetails.getTransferredTo();
		if (transferDetails.getTransferState().equals(TransferState.READY)){
			if (transferDetails.getAmount() > sender.getBalance()) {
				transferDetails.setTransferState(TransferState.CANCELLED);
			} else {
				sender.setBalance(roundCurrency(sender.getBalance() - transferDetails.getAmount()));
				receiver.setBalance(Math.round(receiver.getBalance() + transferDetails.getAmount()));
				userRepository.save(sender);
				userRepository.save(receiver);
				transferDetails.setTransferState(TransferState.TRANSMITTED);
			}
		}
		return transferRepository.save(transferDetails);
	}

	private static double roundCurrency(double value) {
		double factor = Math.pow(10, 2);
		return Math.round(value * factor) / factor;
	}

	@Override
	public List<TransferDetails> getAllTransfersByUser(User transferredBy) {
		return transferRepository.findByTransferredBy(transferredBy);
	}

	@Override
	public List<TransferDetails> getOutgoingTransfers(User currentUser) {
		return transferRepository.findByTransferredBy(currentUser);
	}

	@Override
	public List<TransferDetails> getIncomingTransfers(User currentUser) {
		return transferRepository.findByTransferredTo(currentUser);
	}

}
