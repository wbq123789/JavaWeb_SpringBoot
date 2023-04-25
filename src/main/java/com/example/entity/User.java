package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    int id;
    String name;
    String role;
    String password;
}
