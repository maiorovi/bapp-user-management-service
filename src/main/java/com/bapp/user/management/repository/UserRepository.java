package com.bapp.user.management.repository;

import com.bapp.user.management.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<UserEntity> save(Mono<UserEntity> user);


    Flux<UserEntity> getAll();
}
