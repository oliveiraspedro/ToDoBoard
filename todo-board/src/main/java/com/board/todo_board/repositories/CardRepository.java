package com.board.todo_board.repositories;

import com.board.todo_board.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    List<CardEntity> findByColumnId(Long columnId);
}
