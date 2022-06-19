package com.ownproject.Sorganizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RepReqService {

	@Autowired
	private RepRequestRepository repRequestRepository;
	@Autowired
	private UserRepository userRepository;

	public RepReqService(RepRequestRepository repRequestRepository) {
		this.repRequestRepository = repRequestRepository;
	}

	public List<RepRequest> getUnscheduledRepReqList() {
		return this.repRequestRepository.findAllByIsScheduledFalse();
	}

	public List<RepRequest> getAllRequestsByUserId(Integer userId) {
		User user = userRepository.findById(userId).get();
		return this.repRequestRepository.findAllByUser(user);
	}

	public RepRequest addRepReq(RepRequestForm repReqform, User user) {
		RepRequest repRequest = new RepRequest();
		repRequest.setTimeStamp(new Timestamp(System.currentTimeMillis()));
		repRequest.setClientName(repReqform.getClientName());
		repRequest.setLicencePlate(repReqform.getLicencePlate());
		repRequest.setVinNumber(repReqform.getVinNumber());
		repRequest.setDefectDescription(repReqform.getDefectDescription());
		repRequest.setUser(user);
		repRequest.setPartsOrdered(false);
		repRequest.setScheduled(false);
		repRequest.setFinished(false);
		return this.repRequestRepository.save(repRequest);
	}

	public void deleteByRepReqId(Integer repreqId) {
		this.repRequestRepository.deleteById(repreqId);
	}

	public void delete(RepRequest r) {
		this.repRequestRepository.delete(r);
	}

	public RepRequest findById(Integer id) {
		return this.repRequestRepository.findById(id).get();

	}

	public void save(RepRequest repRequest) {

		this.repRequestRepository.save(repRequest);
	}

	public void save(RepRequestForm r) {
		RepRequest repRequest = new RepRequest();
		Optional<RepRequest> repReq = repRequestRepository.findById(r.getRepReqId());
		if (repReq.isPresent()) {
			repRequest = repReq.get();

		}
		repRequestRepository.save(repRequest);
	}

}
