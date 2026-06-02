package com.solar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String email;
    private String password;
}
