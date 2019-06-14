package com.bapp.user.management.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserEntity {

    private final String id;
    private final String lastName;
    private final String firstName;
    private final int age;
    private final LocalDate birthDay;
}
