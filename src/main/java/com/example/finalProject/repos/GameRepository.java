package com.example.finalProject.repos;

import com.example.finalProject.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findByName(String name);
}
