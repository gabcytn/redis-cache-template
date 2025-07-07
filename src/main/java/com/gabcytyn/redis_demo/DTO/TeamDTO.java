package com.gabcytyn.redis_demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TeamDTO
{
	@NotNull
	@NotBlank
	private String name;

	public TeamDTO(String name)
	{
		this.name = name;
	}

	public TeamDTO()
	{
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
