package com.gabcytyn.redis_demo.DTO;

public class TeamResponseDTO
{
	private Integer id;
	private String name;

	public TeamResponseDTO() {}

	public TeamResponseDTO(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
