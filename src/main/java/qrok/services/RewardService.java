package qrok.services;

import qrok.entitties.Reward;

public interface RewardService {

	public Reward getRewardById(long id);

	public Reward add(Reward reward);

	public void delete(long id);

	public Reward edit(Reward reward);

}
