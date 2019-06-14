package com.bapp.user.management.service.user;

import com.bapp.user.management.entity.UserEntity;
import com.bapp.user.management.model.CreateUserRequest;
import com.bapp.user.management.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> createUser(Mono<CreateUserRequest> request);

    Flux<User> getAllUsers();
}
