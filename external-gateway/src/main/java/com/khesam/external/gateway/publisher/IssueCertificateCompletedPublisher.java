package com.khesam.external.gateway.publisher;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.groups.UniCreate;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IssueCertificateCompletedPublisher {

    private static final String ISSUE_CERTIFICATE_COMPLETED_CHANNEL_NAME = "issue-certificate-completed-channel";
    private final PubSubCommands<String> pubSubCommands;

    public IssueCertificateCompletedPublisher(RedisDataSource dataSource) {
        pubSubCommands = dataSource.pubsub(String.class);
    }

    public Uni<Void> publish(String id) {
        pubSubCommands.publish(
                ISSUE_CERTIFICATE_COMPLETED_CHANNEL_NAME, id
        );
        return Uni.createFrom().voidItem();
    }
}
