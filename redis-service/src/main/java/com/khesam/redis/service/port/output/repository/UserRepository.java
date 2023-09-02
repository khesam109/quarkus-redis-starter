package com.khesam.redis.service.port.output.repository;

import com.khesam.redis.service.domain.model.sessionmanagement.User;

public interface UserRepository {

    User login(String username, String password);
    User getUser(String username);
}
