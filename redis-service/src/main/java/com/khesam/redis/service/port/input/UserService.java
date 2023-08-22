package com.khesam.redis.service.port.input;

import com.khesam.redis.service.domain.model.sessionmanagement.User;
import com.khesam.redis.service.dto.sessionmanagement.UserInfoResponse;

public interface UserService {

    User login(String username, String password);

    UserInfoResponse getInfo(String username);
}
