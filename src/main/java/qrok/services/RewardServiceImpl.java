package qrok.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import qrok.entitties.Reward;
import qrok.repositories.RewardRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RewardServiceImpl implements RewardService {
	@Autowired
	RewardRepository rewardRepository;

	@Override
	public Reward getRewardById(long id) {
		return rewardRepository.getOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Reward add(Reward reward) {
		return rewardRepository.saveAndFlush(reward);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		rewardRepository.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Reward edit(Reward reward) {
		return rewardRepository.saveAndFlush(reward);
	}

	@Override
	public List<Reward> getAllRewards() {
		return rewardRepository.findAll();
	}

	@Override
	public void deleteAll() {
		rewardRepository.deleteAll();
	}
}

