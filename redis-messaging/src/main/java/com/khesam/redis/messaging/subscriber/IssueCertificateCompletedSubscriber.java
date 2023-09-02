package com.khesam.redis.messaging.subscriber;

import com.khesam.redis.service.port.input.message.IssueCertificateResponseListener;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.function.Consumer;

@ApplicationScoped
@Startup
public class IssueCertificateCompletedSubscriber implements Consumer<String> {

    private static final String ISSUE_CERTIFICATE_COMPLETED_CHANNEL_NAME = "issue-certificate-completed-channel";

    private final PubSubCommands.RedisSubscriber subscriber;
    private final IssueCertificateResponseListener issueCertificateResponseListener;

    @Inject
    public IssueCertificateCompletedSubscriber(
            RedisDataSource dataSource,
            IssueCertificateResponseListener issueCertificateResponseListener
    ) {
        PubSubCommands<String> pubSubCommands = dataSource.pubsub(String.class);
        subscriber = pubSubCommands.subscribe(ISSUE_CERTIFICATE_COMPLETED_CHANNEL_NAME, this);
        this.issueCertificateResponseListener = issueCertificateResponseListener;
    }

    @Override
    public void accept(String id) {
        issueCertificateResponseListener.completed(id);
    }

    @PreDestroy
    public void terminate() {
        subscriber.unsubscribe();
    }
}
