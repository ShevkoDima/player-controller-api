package com.dimashevko.playercontrollertest.service;

import com.dimashevko.playercontrollertest.model.Player;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImp implements PlayerService {

    private PlayersList playersList;


    @Override
    public boolean deleteUserById(Integer id) {
        playersList = new PlayersList();
        Optional<Player> optionalPlayer = playersList.findById(id);
        if (optionalPlayer.isPresent()) {
            Player user = optionalPlayer.get();
            playersList.delete(user);
            return true;
        }

        return false;
    }
}
