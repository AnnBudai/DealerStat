package com.example.finalProject.controller;

import com.example.finalProject.domain.Game;
import com.example.finalProject.domain.GameObject;
import com.example.finalProject.domain.User;
import com.example.finalProject.repos.GameObjectRepository;
import com.example.finalProject.repos.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

@Controller
public class GameObjectController {
    @Autowired
    private GameObjectRepository gameObjectRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/user-gameObject/{user}")
    public String userGameObject(@AuthenticationPrincipal User currentUser, @PathVariable User user, Model model,
                                 @RequestParam(required = false) GameObject gameObject) {
        Set<GameObject> gameObjects = user.getGameObject();

        model.addAttribute("gameObjects", gameObjects);
        model.addAttribute("gameObject", gameObject);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userGameObject";
    }

    @PostMapping("/user-gameObject/{user}")
    public String updateGameObject(@AuthenticationPrincipal User currentUser, @PathVariable Integer user,
                                   @RequestParam String game_name,
                                   @RequestParam("title") String title, @RequestParam("text") String text,
                                   @RequestParam("file") MultipartFile file, @RequestParam("id") GameObject gameObject)
            throws IOException {
        if (gameObject.getUser().equals(currentUser)) {
            Game game = new Game(game_name);
            Game gameDB = gameRepository.findByName(game_name);
            if (gameDB == null) {
                gameRepository.save(game);
            }

            if (!StringUtils.isEmpty(title)) {
                gameObject.setTitle(title);
            }

            if (!StringUtils.isEmpty(text)) {
                gameObject.setText(text);
            }

            gameObject.setGame(gameRepository.findByName(game_name));
            saveFile(gameObject, file);
            gameObject.setUpdated_at(LocalDate.now());

            gameObjectRepository.save(gameObject);
        }

        return "redirect:/user-gameObject/" + user;
    }

    private void saveFile(@Valid GameObject gameObject, @RequestParam("file") MultipartFile file)
            throws IOException {
        MainController.save(gameObject, file, uploadPath);
    }
}
