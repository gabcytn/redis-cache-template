package com.gabcytyn.redis_demo.Repository;

import com.gabcytyn.redis_demo.Entity.Team;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends ListCrudRepository<Team, Integer> {}
