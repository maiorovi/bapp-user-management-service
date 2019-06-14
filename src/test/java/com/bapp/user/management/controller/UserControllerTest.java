package com.bapp.user.management.controller;

import com.bapp.user.management.config.ControllerConfig;
import com.bapp.user.management.entity.UserEntity;
import com.bapp.user.management.model.CreateUserRequest;
import com.bapp.user.management.model.User;
import com.bapp.user.management.prototype.UserPrototype;
import com.bapp.user.management.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.bapp.user.management.controller.UserController.Links.USER_URL;
import static com.bapp.user.management.prototype.CreateUserRequestPrototype.createUserRequest;
import static com.bapp.user.management.prototype.UserEntityPrototype.newUserEntity;
import static com.bapp.user.management.prototype.UserPrototype.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class UserControllerTest {

    private UserController userController;
    private UserService userService;
    private ControllerConfig controllerConfig;

    private WebTestClient client;
    private User user;
    private CreateUserRequest createUserRequest;

    private ObjectMapper MAPPER = new ObjectMapper();


    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
        controllerConfig = new ControllerConfig();
        createUserRequest = createUserRequest();

        user = aNewUser();

        doAnswer(invocation -> {
            Mono<CreateUserRequest> request = invocation.getArgument(0);

            StepVerifier.create(request)
                    .expectNext(createUserRequest)
                    .verifyComplete();

            return Mono.just(user);
        }).when(userService).createUser(any());

        client = WebTestClient.bindToRouterFunction(controllerConfig.userController(userController))
                .build();
    }

    @Test
    void createsAndReturns204StatusAndLocationHeader() {
        client.post()
                .uri(USER_URL)
                .body(Mono.just(createUserRequest), CreateUserRequest.class)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectHeader()
                .valueEquals("Location", USER_URL + "/1");
    }

    @Test
    void retrievesAllUsers() throws JsonProcessingException {
        List<User> user = List.of(this.user, aNewUser());
        doReturn(Flux.fromIterable(user)).when(userService).getAllUsers();

        client.get()
                .uri(USER_URL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(MAPPER.writeValueAsString(user));
    }
}