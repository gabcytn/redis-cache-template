package com.gabcytyn.redis_demo.Repository;

import com.gabcytyn.redis_demo.Entity.Player;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends ListCrudRepository<Player, Integer>
{

}