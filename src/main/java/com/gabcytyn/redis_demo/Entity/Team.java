package com.gabcytyn.redis_demo.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teams")
public class Team
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Player> players;

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

	public Set<Player> getPlayers()
	{
		return players;
	}

	public void setPlayers(Set<Player> players)
	{
		this.players = players;
	}

	@Override
	public String toString()
	{
		return "Team{" +
						"id=" + id +
						", name='" + name + '\'' +
						", players=" + players +
						'}';
	}
}
