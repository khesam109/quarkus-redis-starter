package com.khesam.redis.repository.entity;

public class UserEntity {

    private final String nationalCode;
    private final String username;
    private final String password;
    private final String firstname;
    private final String lastname;

    private UserEntity(Builder builder) {
        nationalCode = builder.nationalCode;
        username = builder.username;
        password = builder.password;
        firstname = builder.firstname;
        lastname = builder.lastname;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public static final class Builder {
        private String nationalCode;
        private String username;
        private String password;
        private String firstname;
        private String lastname;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder nationalCode(String val) {
            nationalCode = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
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

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
