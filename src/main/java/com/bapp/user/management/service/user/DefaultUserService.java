package com.bapp.user.management.service.user;

import com.bapp.user.management.entity.UserEntity;
import com.bapp.user.management.model.CreateUserRequest;
import com.bapp.user.management.model.User;
import com.bapp.user.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<User> createUser(Mono<CreateUserRequest> request) {
        Mono<UserEntity> userEntityMono = fromCreateUserRequest(request);
        return userRepository.save(userEntityMono)
                .map(User::of);
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.getAll()
                .map(User::of);
    }

    private Mono<UserEntity> fromCreateUserRequest(Mono<CreateUserRequest> request) {
        return request
                .map(this::toUserEntity);
    }

    private UserEntity toUserEntity(CreateUserRequest request) {
        return UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .age(request.getAge())
                .birthDay(request.getBirthDay())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .build();
    }
}
