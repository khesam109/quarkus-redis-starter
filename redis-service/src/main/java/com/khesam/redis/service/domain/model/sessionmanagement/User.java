package com.khesam.redis.service.domain.model.sessionmanagement;

import com.khesam.redis.service.domain.model.BaseModel;
import com.khesam.redis.service.domain.valueobject.UserId;

public class User extends BaseModel<UserId> {

    private final String username;
    private final String firstname;
    private final String lastname;

    private User(Builder builder) {
        super.setId(builder.userId);
        username = builder.username;
        firstname = builder.firstname;
        lastname = builder.lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public static final class Builder {
        private UserId userId;
        private String username;
        private String firstname;
        private String lastname;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(UserId val) {
            userId = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder firstname(String val) {
            firstname = val;
            return this;
        }

        public Builder lastname(String val) {
            lastname = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
