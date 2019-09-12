package com.example.finalProject.repos;

import com.example.finalProject.domain.GameObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameObjectRepository extends CrudRepository<GameObject, Long> {
    @Query("SELECT v FROM GameObject v WHERE v.title=:title")
    List<GameObject> findByTitle(String title);
}