package com.gabcytyn.redis_demo.Controller;

import com.gabcytyn.redis_demo.DTO.TeamDTO;
import com.gabcytyn.redis_demo.DTO.TeamResponseDTO;
import com.gabcytyn.redis_demo.Entity.Team;
import com.gabcytyn.redis_demo.Repository.TeamRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController
{
	private final TeamRepository teamRepository;

	public TeamController (TeamRepository teamRepository)
	{
		this.teamRepository = teamRepository;
	}

	@PostMapping
	public ResponseEntity<Void> createTeam (@RequestBody @Valid TeamDTO teamDTO)
	{
		Team team = new Team();
		team.setName(teamDTO.getName());
		teamRepository.save(team);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<TeamResponseDTO>> getTeams ()
	{
		List<Team> teams = teamRepository.findAll();
		List<TeamResponseDTO> teamResponseDTOList = new ArrayList<>();
		for (Team team : teams) {
			TeamResponseDTO teamResponseDTO = new TeamResponseDTO(team.getId(), team.getName());
			teamResponseDTOList.add(teamResponseDTO);
		}
		return new ResponseEntity<>(teamResponseDTOList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeamResponseDTO> getTeam (@PathVariable int id)
	{
		Optional<Team> team = teamRepository.findById(id);
		if (team.isPresent()) {
			Team teamPresent = team.get();
			TeamResponseDTO teamResponseDTO = new TeamResponseDTO(teamPresent.getId(), teamPresent.getName());
			return new ResponseEntity<>(teamResponseDTO, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
