package com.uep.wap.service;

import com.uep.wap.model.Game;
import com.uep.wap.repository.GameRepository;
import com.uep.wap.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void addGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setName(gameDTO.getName());
        game.setDescription(gameDTO.getDescription());
        game.setGenre(gameDTO.getGenre());
        game.setEngine(gameDTO.getEngine());
        game.setPlatform(gameDTO.getPlatform());
        game.setPhase(gameDTO.getPhase());
        game.setReleaseDate(gameDTO.getReleaseDate());
        game.setArchived(gameDTO.getArchived());
        gameRepository.save(game);
        System.out.println("Game added");
    }

    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }
}