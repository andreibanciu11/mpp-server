package com.example.footballnewsserver.repository;

import com.example.footballnewsserver.model.Player;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Player p WHERE p.id = :id")
    void deleteById(Long id);
}
