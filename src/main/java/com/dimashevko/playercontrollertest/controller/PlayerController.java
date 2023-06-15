package com.dimashevko.playercontrollertest.controller;

import com.dimashevko.playercontrollertest.exceptions.PlayerNotFoundException;
import com.dimashevko.playercontrollertest.service.PlayerID;
import com.dimashevko.playercontrollertest.model.Player;
import com.dimashevko.playercontrollertest.service.PlayerService;
import com.dimashevko.playercontrollertest.service.PlayersList;
import com.dimashevko.playercontrollertest.validators.ErrorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PlayerController {
    private PlayersList playersList;

    @Autowired
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayersList playersList) {
        this.playersList = playersList;
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException() {
        ErrorResponse errorResponse = new ErrorResponse("Player not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @PostMapping("/player/create")
    public ResponseEntity<?> createPlayer(@Valid @RequestBody final Player player, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }

            ErrorResponse errorResponse = new ErrorResponse("Validation failed", errorMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        PlayerID playerID = new PlayerID(UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(playerID);
    }

    @GetMapping("/player")
    public Player getPlayer(@Valid @RequestParam Integer id) {
        Optional<Player> player = playersList.getPlayer(id);
        if (player.isPresent()) {
            return player.get();
        }
        throw new PlayerNotFoundException();
    }

    @PatchMapping("/player/update/{id}")
    public ResponseEntity<?> updatePlayer(@Valid @RequestBody final Player updatedPlayer, BindingResult bindingResult,  @PathVariable("id") Integer id) {
        if (playersList.getPlayer(id).isEmpty()) {
            throw new PlayerNotFoundException();
        }
        if (bindingResult.hasErrors()) {
            List<String> errorMessages1 = new ArrayList<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages1.add(error.getDefaultMessage());
            }

            ErrorResponse errorResponse = new ErrorResponse("Validation failed", errorMessages1);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } else {
            Optional<Player> existingPlayer = playersList.getPlayer(id);
            if (updatedPlayer.getAge() != null) {
                existingPlayer.ifPresent(e -> e.setAge(updatedPlayer.getAge()));
            }
            if (updatedPlayer.getGender() != null) {
                existingPlayer.ifPresent(e -> e.setGender(updatedPlayer.getGender()));
            }
            if (updatedPlayer.getLogin() != null) {
                existingPlayer.ifPresent(e -> e.setLogin(updatedPlayer.getLogin()));
            }
            if (updatedPlayer.getPassword() != null) {
                existingPlayer.ifPresent(e -> e.setPassword(updatedPlayer.getPassword()));
            }
            if (updatedPlayer.getRole() != null) {
                existingPlayer.ifPresent(e -> e.setRole(updatedPlayer.getRole()));
            }
            if(updatedPlayer.getScreenName() != null){
                existingPlayer.ifPresent(e -> e.setScreenName(updatedPlayer.getScreenName()));
            }

            playersList.save(existingPlayer);

            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(existingPlayer);
        }
    }

    @DeleteMapping("/player/delete/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") final Integer id) {
        boolean deleted = playerService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("User deleted successfully");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete user");
        }
    }
}
