package com.board.todo_board.repositories;

import com.board.todo_board.entities.BlockedCardEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface BlockedCardRepository extends JpaRepository<BlockedCardEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE BlockedCardEntity bc SET bc.unblockCause = ?2, bc.unblockedIn = ?3 WHERE id = ?1")
    void alterUnblockCauseAndUnblockedIn(Long blockedCardId, String unblockCause, LocalDateTime unblockedIn);
}
