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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameObjectRepository gameObjectRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String title, Model model) {
        Iterable<GameObject> gameObjects;
        if (title != null && !title.isEmpty()) {
            gameObjects = gameObjectRepository.findByTitle(title);
        } else {
            gameObjects = gameObjectRepository.findAll();
        }
        model.addAttribute("gameObjects", gameObjects);
        model.addAttribute("title", title);
        return "main";
    }

    @PostMapping("/main")
    public String addGameObject(@AuthenticationPrincipal User user, @RequestParam String game_name,
                                @Valid GameObject gameObject, BindingResult bindingResult, Model model,
                                @RequestParam("file") MultipartFile file) throws IOException {
        Game game = new Game(game_name);
        Game gameDB = gameRepository.findByName(game_name);
        if (gameDB == null) {
            gameRepository.save(game);
        }
        gameObject.setUser(user);
        gameObject.setGame(gameRepository.findByName(game_name));
        gameObject.setCreated_at(LocalDate.now());

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("gameObject", gameObject);
        } else {
            saveFile(gameObject, file);
            model.addAttribute("gameObject", null);
            gameObjectRepository.save(gameObject);
        }

        Iterable<GameObject> gameObjects = gameObjectRepository.findAll();
        model.addAttribute("gameObjects", gameObjects);
        return "main";
    }

    private void saveFile(@Valid GameObject gameObject, @RequestParam("file") MultipartFile file) throws IOException {
        save(gameObject, file, uploadPath);
    }

    static void save(@Valid GameObject gameObject, @RequestParam("file") MultipartFile file, String uploadPath) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            gameObject.setFilename(resultFilename);
        }
    }
}

