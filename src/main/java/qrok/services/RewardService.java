package qrok.services;

import java.util.List;

import qrok.entitties.Reward;

public interface RewardService {

	public Reward getRewardById(long id);

	public Reward add(Reward reward);

	public void delete(long id);
	
	public List<Reward> getAllRewards();

	public Reward edit(Reward reward);

}
