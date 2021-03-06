package qrok.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import qrok.entitties.Reward;

public interface RewardRepository extends JpaRepository<Reward, Long> {
	
	@Override
    @Query("select r from Reward r where reward_id = ?1")
	Reward getOne(Long id);
	
	//@Query("SELECT p FROM Reward p WHERE author_id = ?1")
    //List<Reward> getRewardsByAuthorId(long id);
}
