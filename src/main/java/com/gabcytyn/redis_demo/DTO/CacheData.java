package com.gabcytyn.redis_demo.DTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "cacheData")
public class CacheData
{
	@Id
	private String key;
	private String value;
	@TimeToLive
	private Long expiration = 180L; // 180 seconds default

	public CacheData()
	{
	}

	public CacheData(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	public CacheData(String key, String value, Long expiration)
	{
		this.key = key;
		this.value = value;
		this.expiration = expiration;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Long getExpiration()
	{
		return expiration;
	}

	public void setExpiration(Long expiration)
	{
		this.expiration = expiration;
	}

	@Override
	public String toString()
	{
		return "CacheData{" +
				"key='" + key + '\'' +
				", value=" + value +
				'}';
	}
}
