package com.bapp.user.management.controller;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class HelloController {

    public Mono<ServerResponse> sayHello(ServerRequest request) {
        return ServerResponse.ok()
                .body(Mono.just("hello world"), String.class);
    }
}
