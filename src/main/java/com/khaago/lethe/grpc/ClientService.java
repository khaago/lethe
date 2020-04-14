package com.khaago.lethe.grpc;

import com.khaago.lethe.*;
import com.khaago.lethe.exception.ExceptionService;
import com.khaago.lethe.service.RegistrationService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends BrokerGrpc.BrokerImplBase {

    private final RegistrationService registrationService;
    private final ExceptionService exceptionService;

    @Autowired
    public ClientService(RegistrationService registrationService, ExceptionService exceptionService) {
        this.registrationService = registrationService;
        this.exceptionService = exceptionService;
    }

    @Override
    public void register(RegistrationRequest request, StreamObserver<RegistrationResponse> observer) {
        RegistrationResponse registrationResponse = RegistrationResponse
                .newBuilder()
                .setClientName(request.getClientName())
                .build();
        registrationService.registerClient(request);
        observer.onNext(registrationResponse);
        observer.onCompleted();
    }

    @Override
    public void subscribe(SubscribeOptions options, StreamObserver<Topic> observer) {
        try {
            // TODO grab the requested topic from repo
            Topic t = Topic.newBuilder().setRetention(1L / 1).build();
            observer.onNext(t);
            observer.onCompleted();
        } catch (Exception e) {
            exceptionService.generateOnError(observer, e, e.getMessage());
        }
    }

    @Override
    public void listen(ListenOptions options, StreamObserver<Event> observer) {
        throw new RuntimeException("Not here bro");
    }
}
