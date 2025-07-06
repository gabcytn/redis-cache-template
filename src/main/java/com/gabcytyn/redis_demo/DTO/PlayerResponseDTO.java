package com.gabcytyn.redis_demo.DTO;

public class PlayerResponseDTO
{
	private Integer id;
	private String fullName;
	private String teamName;

	public PlayerResponseDTO(Integer id, String fullName, String teamName)
	{
		this.id = id;
		this.fullName = fullName;
		this.teamName = teamName;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getTeamName()
	{
		return teamName;
	}

	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}

	@Override
	public String toString()
	{
		return "PlayerResponseDTO{" +
						"id=" + id +
						", fullName='" + fullName + '\'' +
						", teamName='" + teamName + '\'' +
						'}';
	}
}
