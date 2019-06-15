package com.bapp.user.management.repository;

import com.bapp.user.management.entity.UserEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryUserRepository implements UserRepository {
    private final ConcurrentMap<String, UserEntity> userEntities = new ConcurrentHashMap<>();

    @Override
    public Mono<UserEntity> save(Mono<UserEntity> user) {

        return user.doOnNext(e -> {
            userEntities.put(e.getId(), e);
        });
    }

    @Override
    public Flux<UserEntity> getAll() {
        return Flux.fromIterable(userEntities.values());
    }
}
