package com.khesam.redis.repository.entity;

public class CertificateEntity {

    private final String id;
    private final String csr;
    private final String status;

    private CertificateEntity(Builder builder) {
        id = builder.id;
        csr = builder.csr;
        status = builder.status;
    }

    public String getId() {
        return id;
    }

    public String getCsr() {
        return csr;
    }

    public String getStatus() {
        return status;
    }

    public static final class Builder {
        private String id;
        private String csr;
        private String status;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder csr(String val) {
            csr = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public CertificateEntity build() {
            return new CertificateEntity(this);
        }
    }
}
