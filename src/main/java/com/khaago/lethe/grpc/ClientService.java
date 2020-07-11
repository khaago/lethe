package com.khaago.lethe.grpc;

import com.khaago.lethe.*;
import com.khaago.lethe.exception.ExceptionService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manages subscribed clients
 */
@Service
public class ClientService extends BrokerGrpc.BrokerImplBase {

    private final ExceptionService exceptionService;

    @Autowired
    public ClientService(ExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }

    @Override
    public void subscribe(SubscribeOptions options, StreamObserver<Topic> observer) {
        try {
            // TODO grab the requested topic from repo
            Topic t = Topic.newBuilder().setRetention(1L / 1).build();
            observer.onNext(t);
            observer.onCompleted();
        } catch (Exception e) {
            exceptionService.generateOnError(observer, e, Status.INTERNAL, e.getMessage());
        }
    }

    @Override
    public void listen(ListenOptions options, StreamObserver<Event> observer) {
        throw new RuntimeException("Not here bro");
    }
}
