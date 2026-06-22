package com.movieflix.controllers;

import com.movieflix.controllers.request.StreamingRequest;
import com.movieflix.controllers.response.StreamingResponse;
import com.movieflix.entities.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.services.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;


    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAll() {
        var streamings = streamingService.findAll().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streamings);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request) {

        //Pegamos o request enviado pelo usuario e transformamos em Category entity
        Streaming streaming = StreamingMapper.toStreaming(request);
        //o service vai retornar um novo Category
        Streaming streaming1 = streamingService.save(streaming);
        //Apos isso convertemos para CategoryResponse
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(streaming1));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable UUID id) {
        return streamingService.findById(id)
                .map(x -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(x)))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteById(@PathVariable UUID id) {
        if (streamingService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> update(@PathVariable UUID id, @RequestBody StreamingRequest request) {
        Optional<Streaming> salvo = streamingService.update(id, StreamingMapper.toStreaming(request));
        return salvo.map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
