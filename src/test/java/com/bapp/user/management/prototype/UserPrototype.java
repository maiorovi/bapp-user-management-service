package com.bapp.user.management.prototype;

import com.bapp.user.management.model.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class UserPrototype {

    public static User aNewUser() {
        return User.builder()
                .id("1")
                .birthDay(LocalDate.of(2000, 5, 25))
                .firstName("John")
                .lastName("Doe")
                .age(19)
                .build();
    }
}
