package com.movieflix.repositories;

import com.movieflix.entities.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StreamingRepository extends JpaRepository<Streaming, UUID> {
}
