package com.example.footballnewsserver.repository;

import com.example.footballnewsserver.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Page<Team> findAll(Pageable pageable);
    Optional<Team> findTeamById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
}
