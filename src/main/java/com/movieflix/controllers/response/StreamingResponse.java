package com.movieflix.controllers.response;

import lombok.Builder;

import java.util.UUID;
@Builder
public record StreamingResponse(UUID id, String name) {

}
