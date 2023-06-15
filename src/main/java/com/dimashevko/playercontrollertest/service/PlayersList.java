package com.dimashevko.playercontrollertest.service;

import com.dimashevko.playercontrollertest.model.Player;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayersList {

    private List<Player> playerList;

    public PlayersList(){
        playerList = new ArrayList<>();

        Player player1 = new Player(1, 20,"male","firstPlayer","firstPassword","user","John");
        Player player2 = new Player(2, 30,"female","secondPlayer", "secondPassword","user","Ana" );
        Player player3 = new Player(3, 40,"male","thirdPlayer", "secondPassword","user","Borat" );
        playerList.addAll(Arrays.asList(player1,player2,player3));
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    private Map<Integer, Optional<Player>> playerMap;
    public void save(Optional<Player> player) {
        playerMap = new HashMap<>();
        playerMap.put(player.get().getId(), player);
    }


    public Optional<Player> getPlayer(Integer id){
        Optional optional = Optional.empty();
        for (Player player:playerList){
            if(id == player.getId()){
                optional = Optional.of(player);
                return optional;
            }
        }
        return optional;
    }

    public Optional<Player> findById(Integer id) {
        return playerList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public void delete(Player player) {
        playerList.remove(player);
    }

}
