package com.gabcytyn.redis_demo.Repository;

import com.gabcytyn.redis_demo.DTO.CacheData;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamCacheRepository extends ListCrudRepository<CacheData, String>
{

}
