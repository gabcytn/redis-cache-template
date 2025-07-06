package com.gabcytyn.redis_demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@ManyToOne
	@JoinColumn(name = "team_id", referencedColumnName = "id")
	private Team team;

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

	public Team getTeam()
	{
		return team;
	}

	public void setTeam(Team team)
	{
		this.team = team;
	}

	@Override
	public String toString()
	{
		return "Player{" +
						"id=" + id +
						", fullName='" + fullName + '\'' +
						", team=" + team +
						'}';
	}
}
