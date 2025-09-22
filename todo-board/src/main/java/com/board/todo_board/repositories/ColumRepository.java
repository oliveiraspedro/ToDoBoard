package com.board.todo_board.repositories;

import com.board.todo_board.entities.ColumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumRepository extends JpaRepository<ColumEntity, Long> {

    //@Query("SELECT ColumEntity WHERE board_id = ?1")
    List<ColumEntity> findByBoardId(Long boardId);

}
