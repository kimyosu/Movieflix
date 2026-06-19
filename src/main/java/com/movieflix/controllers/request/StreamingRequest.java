package com.movieflix.controllers.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
}
