package com.khesam.external.gateway.subscriber;

import com.khesam.external.gateway.IssueCertificateService;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.runtime.Startup;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

@ApplicationScoped
@Startup
public class IssueCertificateRequestSubscriber implements Consumer<String> {

    private static final String ISSUE_CERTIFICATE_REQUEST_CHANNEL_NAME = "issue-certificate-request-channel";

    private final PubSubCommands.RedisSubscriber subscriber;
    private final IssueCertificateService issueCertificateService;

    @Inject
    public IssueCertificateRequestSubscriber(
            RedisDataSource dataSource,
            IssueCertificateService issueCertificateService
    ) {
        this.issueCertificateService = issueCertificateService;
        PubSubCommands<String> pubSubCommands = dataSource.pubsub(String.class);
        subscriber = pubSubCommands.subscribe(ISSUE_CERTIFICATE_REQUEST_CHANNEL_NAME, this);
    }


    @Override
    public void accept(String id) {
        issueCertificateService.issueCertificate(id);
//        boolean result = issueCertificate();
//        if (result) {
//            issueCertificateCompletedPublisher.publish(id);
//        } else {
//            issueCertificateFailedPublisher.publish(id);
//        }
    }

    @PreDestroy
    public void terminate() {
        subscriber.unsubscribe();
    }


}
