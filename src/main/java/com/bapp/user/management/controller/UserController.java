package com.bapp.user.management.controller;

import com.bapp.user.management.entity.UserEntity;
import com.bapp.user.management.model.CreateUserRequest;
import com.bapp.user.management.model.User;
import com.bapp.user.management.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.bapp.user.management.controller.UserController.Links.LOCATION_HEADER;
import static com.bapp.user.management.controller.UserController.Links.USER_URL;

@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        Mono<CreateUserRequest> createUserRequest = serverRequest.bodyToMono(CreateUserRequest.class);

        return userService.createUser(createUserRequest)
                .flatMap(this::createServerResponse);
    }

    private Mono<ServerResponse> createServerResponse(User user) {
        return ServerResponse.noContent()
                .header(LOCATION_HEADER, createLocationHeaderValue(user))
                .build();
    }

    private String createLocationHeaderValue(User user) {
        return USER_URL + "/" + user.getId();
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(userService.getAllUsers(), User.class);
    }

    public static class Links {
        public static final String USER_URL = "/user";
        public static final String USER_BY_ID = USER_URL + "/{userId}";
        public static final String LOCATION_HEADER = "Location";
    }
}
