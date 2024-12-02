package com.stackoverflow.repository;

import com.stackoverflow.model.Replay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplayRepository extends JpaRepository<Replay,Long> {
}
