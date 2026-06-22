package com.movieflix.services;

import com.movieflix.entities.Streaming;
import com.movieflix.repositories.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public List<Streaming> findAll() {
        return streamingRepository.findAll();
    }


    public Streaming save(Streaming streaming) {
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> findById(UUID id) {
        return streamingRepository.findById(id);
    }

    public boolean deleteById(UUID id) {
        if (streamingRepository.existsById(id)) {
            streamingRepository.deleteById(id);
            return true;
        }
        return false;

    }

    public Optional<Streaming> update(UUID id, Streaming streaming) {
        return streamingRepository.findById(id)
                .map(existing -> {
                    existing.setName(streaming.getName());
                    return streamingRepository.save(existing);
                });
    }
}
