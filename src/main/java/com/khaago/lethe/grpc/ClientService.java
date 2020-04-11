package com.khaago.lethe.grpc;

import com.khaago.lethe.BrokerGrpc;
import com.khaago.lethe.RegistrationRequest;
import com.khaago.lethe.RegistrationResponse;
import io.grpc.stub.StreamObserver;

public class ClientService extends BrokerGrpc.BrokerImplBase {

    @Override
    public void register(RegistrationRequest request, StreamObserver<RegistrationResponse> observer) {
        RegistrationResponse registrationResponse = RegistrationResponse
                .newBuilder()
                .setClientName(request.getClientName())
                .build();
        observer.onNext(registrationResponse);
        observer.onCompleted();
    }
}
