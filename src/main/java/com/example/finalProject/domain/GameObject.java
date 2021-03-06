package com.example.finalProject.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class GameObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Please, add a game object title")
    private String title;

    @Length(max = 255, message = "Description of game object too long")
    private String text;

    private LocalDate created_at;
    private LocalDate updated_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private Game game;

    @OneToMany(mappedBy = "gameObject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comment;

    private String filename;

    public GameObject() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updated_at = LocalDate.now();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        updated_at = LocalDate.now();
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user_id) {
        this.user = user_id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game_id) {
        this.game = game_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }
}
