package com.khesam.redis.service.port.output;

import com.khesam.redis.service.domain.model.sessionmanagement.User;

public interface UserRepository {

    User login(String username, String password);
    User getUser(String username);
}
