package com.example.finalProject.repos;

import com.example.finalProject.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query("SELECT v FROM Comment v WHERE v.message=:message")
    List<Comment> findByMessage(String message);
}