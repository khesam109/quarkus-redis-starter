package com.khesam.external.gateway.publisher;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IssueCertificateFailedPublisher {

    private static final String ISSUE_CERTIFICATE_FAILED_CHANNEL_NAME = "issue-certificate-failed-channel";

    private final PubSubCommands<String> pubSubCommands;

    public IssueCertificateFailedPublisher(RedisDataSource dataSource) {
        pubSubCommands = dataSource.pubsub(String.class);
    }

    public void publish(String id) {
        pubSubCommands.publish(
                ISSUE_CERTIFICATE_FAILED_CHANNEL_NAME, id
        );
    }
}
