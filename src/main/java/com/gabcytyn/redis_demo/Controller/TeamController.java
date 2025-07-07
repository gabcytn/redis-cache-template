package com.gabcytyn.redis_demo.Controller;

import com.gabcytyn.redis_demo.DTO.TeamDTO;
import com.gabcytyn.redis_demo.DTO.TeamResponseDTO;
import com.gabcytyn.redis_demo.Entity.Team;
import com.gabcytyn.redis_demo.Repository.TeamRepository;
import com.gabcytyn.redis_demo.Service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController
{
  private final TeamRepository teamRepository;
  private final TeamService teamService;

  public TeamController(TeamRepository teamRepository, TeamService teamService)
  {
    this.teamRepository = teamRepository;
    this.teamService = teamService;
  }

  @PostMapping
  public ResponseEntity<Void> createTeam(@RequestBody @Valid TeamDTO teamDTO)
  {
    Team team = new Team();
    team.setName(teamDTO.getName());
    teamRepository.save(team);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<TeamResponseDTO>> getTeams()
  {
    try {
      return new ResponseEntity<>(teamService.getTeams(), HttpStatus.OK);
    } catch (Exception e) {
      System.err.println("Error getting list of teams!");
      System.err.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeamResponseDTO> getTeam(@PathVariable int id)
  {
    try {
      Optional<TeamResponseDTO> team = teamService.getTeam(id);
      if (team.isPresent()) {
        return new ResponseEntity<>(team.get(), HttpStatus.OK);
      }

      throw new Exception("Team not found");
    } catch (Exception e) {
      System.err.println("Error getting team");
      System.err.println(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

  }
}
