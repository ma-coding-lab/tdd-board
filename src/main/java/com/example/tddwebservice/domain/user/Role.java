package com.example.tddwebservice.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    Guest("ROLE_GUEST", "손님"),
    User("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
