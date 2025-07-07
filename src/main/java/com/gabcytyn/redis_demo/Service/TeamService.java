package com.gabcytyn.redis_demo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabcytyn.redis_demo.DTO.CacheData;
import com.gabcytyn.redis_demo.DTO.TeamResponseDTO;
import com.gabcytyn.redis_demo.Entity.Team;
import com.gabcytyn.redis_demo.Repository.TeamCacheRepository;
import com.gabcytyn.redis_demo.Repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService
{
  private final TeamRepository teamRepository;
  private final TeamCacheRepository teamCacheRepository;
  private final ObjectMapper objectMapper;

  public TeamService(TeamRepository teamRepository, TeamCacheRepository teamCacheRepository, ObjectMapper objectMapper)
  {
    this.teamRepository = teamRepository;
    this.teamCacheRepository = teamCacheRepository;
    this.objectMapper = objectMapper;
  }

  public List<TeamResponseDTO> getTeams() throws JsonProcessingException
  {
    Optional<CacheData> cacheData = teamCacheRepository.findById("teams");

    // cache hit
    if (cacheData.isPresent()) {
      System.out.println("Cache hit!");
      String teamsAsString = cacheData.get().getValue();
      TypeReference<List<TeamResponseDTO>> mapType = new TypeReference<>()
      {
      };
      return objectMapper.readValue(teamsAsString, mapType);
    }

    // cache miss
    System.out.println("Cache miss!");
    List<Team> teams = teamRepository.findAll();
    List<TeamResponseDTO> teamResponseDTOList = new ArrayList<>();

    for (Team team : teams) {
      TeamResponseDTO teamResponseDTO = new TeamResponseDTO(team.getId(), team.getName());
      teamResponseDTOList.add(teamResponseDTO);
    }

    // save db query in cache
    try {
      String teamsAsString = objectMapper.writeValueAsString(teamResponseDTOList);
      teamCacheRepository.save(new CacheData("teams", teamsAsString));
      System.out.println("Successfully saved in cache!");
    } catch (Exception e) {
      System.err.println("Failed to save query in cache!");
      System.err.println(e.getMessage());
    }

    return teamResponseDTOList;
  }

  public Optional<TeamResponseDTO> getTeam(int id) throws JsonProcessingException
  {
    Optional<CacheData> cachedTeam = teamCacheRepository.findById("team-" + id);
    if (cachedTeam.isPresent()) {
      System.out.println("Cache hit!");
      String teamAsString = cachedTeam.get().getValue();
      TypeReference<TeamResponseDTO> mapType = new TypeReference<>()
      {
      };
      return Optional.of(objectMapper.readValue(teamAsString, mapType));
    }

    System.out.println("Cache miss!");
    Optional<Team> team = teamRepository.findById(id);
    if (team.isPresent()) {
      Team teamPresent = team.get();
      TeamResponseDTO teamResponseDto = new TeamResponseDTO(teamPresent.getId(), teamPresent.getName());
      // write to cache
      String teamAsString = objectMapper.writeValueAsString(teamResponseDto);
      teamCacheRepository.save(new CacheData("team-" + id, teamAsString));
      return Optional.of(teamResponseDto);
    }

    return Optional.empty();
  }
}
