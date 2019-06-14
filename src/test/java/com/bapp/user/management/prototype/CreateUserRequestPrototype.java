package com.bapp.user.management.prototype;

import com.bapp.user.management.model.CreateUserRequest;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class CreateUserRequestPrototype {

    public static CreateUserRequest createUserRequest() {
        return CreateUserRequest.builder()
                .age(29)
                .birthDay(LocalDate.of(2000, 5, 25))
                .firstName("John")
                .lastName("Doe")
                .build();
    }
}
