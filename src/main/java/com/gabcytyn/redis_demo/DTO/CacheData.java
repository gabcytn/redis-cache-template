package com.gabcytyn.redis_demo.DTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("cacheData")
public class CacheData
{
	@Id
	private String key;
	private String value;

	public CacheData()
	{
	}

	public CacheData(String key, String value)
	{
		this.key = key;
		this.value = value;
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

	@Override
	public String toString()
	{
		return "CacheData{" +
				"key='" + key + '\'' +
				", value=" + value +
				'}';
	}
}
