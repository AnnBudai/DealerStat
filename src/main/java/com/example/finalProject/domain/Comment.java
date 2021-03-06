package com.example.finalProject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Please, add a message")
    private String message;

    private LocalDate create_at;
    private boolean approved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private GameObject gameObject;

    public Comment() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user != null ? user.getUsername() : "<none>";
    }

    public void setUser(User user_id) {
        this.user = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
