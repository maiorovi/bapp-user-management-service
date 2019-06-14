package com.bapp.user.management.repository;

import com.bapp.user.management.entity.UserEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryUserRepository implements UserRepository {
    private final ConcurrentMap<String, Mono<UserEntity>> userEntities = new ConcurrentHashMap<>();

    @Override
    public Mono<UserEntity> save(Mono<UserEntity> user) {
        String id = user.map(UserEntity::getId).block();
        userEntities.put(id, user);

        return user;
    }

    @Override
    public Flux<UserEntity> getAll() {
        return Flux.fromIterable(userEntities.values())
                    .flatMap(r -> r);
    }
}
