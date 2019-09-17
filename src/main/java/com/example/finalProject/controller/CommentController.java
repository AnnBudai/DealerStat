package com.example.finalProject.controller;

import com.example.finalProject.domain.Comment;
import com.example.finalProject.domain.GameObject;
import com.example.finalProject.domain.User;
import com.example.finalProject.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comment/{gameObject}")
    public String addComment(@PathVariable GameObject gameObject,
                             @RequestParam(required = false) Comment comment, Model model) {
        Set<Comment> comments = gameObject.getComment();

        model.addAttribute("comments", comments);
        model.addAttribute("comment", comment);
        return "comment";
    }

    @PostMapping("/comment/{gameObject}")
    public String addComment(@PathVariable Integer gameObject, @AuthenticationPrincipal User user,
                             @Valid Comment comment, BindingResult bindingResult, Model model,
                             @RequestParam("gameObject") GameObject gameObjectCurrent) {
        comment.setUser(user);
        comment.setCreate_at(LocalDate.now());
        comment.setApproved(true);
        comment.setGameObject(gameObjectCurrent);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("comment", comment);
        } else {
            model.addAttribute("comment", null);
            commentRepository.save(comment);
        }

        model.addAttribute("comment", comment);
        return "redirect:/comment/" + gameObject;
    }

}
