package com.example.vo;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
@Data
@ToString
@NoArgsConstructor

public class LoginRequest {
    String username;
    String password;
}
