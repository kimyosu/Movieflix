package com.movieflix.controllers.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CategoryResponse(UUID id, String name) {
}
