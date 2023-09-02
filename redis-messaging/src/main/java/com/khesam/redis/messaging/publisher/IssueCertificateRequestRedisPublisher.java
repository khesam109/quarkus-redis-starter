package com.khesam.redis.messaging.publisher;

import com.khesam.redis.service.event.IssueCertificateEvent;
import com.khesam.redis.service.port.output.publisher.IssueCertificateRequestPublisher;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IssueCertificateRequestRedisPublisher implements IssueCertificateRequestPublisher {

    private static final String ISSUE_CERTIFICATE_REQUEST_CHANNEL_NAME = "issue-certificate-request-channel";

    private final PubSubCommands<String> pubSubCommands;

    @Inject
    public IssueCertificateRequestRedisPublisher(RedisDataSource dataSource) {
        pubSubCommands = dataSource.pubsub(String.class);
    }

    @Override
    public void publish(IssueCertificateEvent event) {
        pubSubCommands.publish(
                ISSUE_CERTIFICATE_REQUEST_CHANNEL_NAME,
                event.certificate().getId().getValue().toString()
        );
    }
}
