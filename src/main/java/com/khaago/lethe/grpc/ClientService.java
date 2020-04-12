package com.khaago.lethe.grpc;

import com.khaago.lethe.BrokerGrpc;
import com.khaago.lethe.RegistrationRequest;
import com.khaago.lethe.RegistrationResponse;
import com.khaago.lethe.service.RegistrationService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends BrokerGrpc.BrokerImplBase {

    private final RegistrationService registrationService;

    @Autowired
    public ClientService(RegistrationService registrationService) {
        this.registrationService = registrationService;
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
}
