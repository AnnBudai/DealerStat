package com.example.finalProject.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;
    private LocalDate create_at;
    private boolean approved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private User user;

    public Comment(User user, String message, LocalDate create_at) {
        this.user = user;
        this.message = message;
        this.create_at = create_at;
        this.approved = true;
    }

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
}
