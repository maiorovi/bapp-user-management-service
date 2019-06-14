package com.bapp.user.management.model;

import com.bapp.user.management.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private final String id;
    private final String lastName;
    private final String firstName;
    private final int age;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private final LocalDate birthDay;


    public static User of(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .birthDay(userEntity.getBirthDay())
                .age(userEntity.getAge())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build();
    }
}
