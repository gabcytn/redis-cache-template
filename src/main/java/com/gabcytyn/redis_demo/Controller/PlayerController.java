package com.gabcytyn.redis_demo.Controller;

import com.gabcytyn.redis_demo.DTO.ErrorResponse;
import com.gabcytyn.redis_demo.DTO.PlayerDTO;
import com.gabcytyn.redis_demo.DTO.PlayerResponseDTO;
import com.gabcytyn.redis_demo.Entity.Player;
import com.gabcytyn.redis_demo.Entity.Team;
import com.gabcytyn.redis_demo.Repository.PlayerRepository;
import com.gabcytyn.redis_demo.Repository.TeamRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController
{
	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;

	public PlayerController (PlayerRepository playerRepository, TeamRepository teamRepository)
	{
		this.playerRepository = playerRepository;
		this.teamRepository = teamRepository;
	}

	@PostMapping
	public ResponseEntity<ErrorResponse> createPlayer (@RequestBody @Valid PlayerDTO playerDTO)
	{
		Player player = new Player();
		player.setFullName(playerDTO.getFullName());

		Optional<Team> team = teamRepository.findById(playerDTO.getTeamId());
		if (team.isPresent()) {
			player.setTeam(team.get());
			playerRepository.save(player);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}

		return new ResponseEntity<>(new ErrorResponse("Team ID not found."),HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<PlayerResponseDTO>> getPlayers ()
	{
		List<Player> players = playerRepository.findAll();
		List<PlayerResponseDTO> playerResponseDTOList = new ArrayList<>();
		for (Player player : players) {
			PlayerResponseDTO playerResponseDTO = new PlayerResponseDTO(
							player.getId(),
							player.getFullName(),
							player.getTeam().getName()
			);
			playerResponseDTOList.add(playerResponseDTO);
		}
		return new ResponseEntity<>(playerResponseDTOList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlayerResponseDTO> getPlayer (@PathVariable int id)
	{
		Optional<Player> optionalPlayer = playerRepository.findById(id);
		if (optionalPlayer.isPresent()) {
			Player currentPlayer = optionalPlayer.get();
			PlayerResponseDTO player = new PlayerResponseDTO(
							currentPlayer.getId(),
							currentPlayer.getFullName(),
							currentPlayer.getTeam().getName()
			);

			return new ResponseEntity<>(player, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
