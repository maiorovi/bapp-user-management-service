package com.bapp.user.management.prototype;

import com.bapp.user.management.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class UserEntityPrototype {

    public static UserEntity newUserEntity() {
        return UserEntity.builder()
                .id("1")
                .birthDay(LocalDate.of(2000, 5, 25))
                .firstName("John")
                .lastName("Doe")
                .age(19)
                .build();
    }
}
