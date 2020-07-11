package com.khaago.lethe.exception;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    @SuppressWarnings("rawtypes")
    public void generateOnError(StreamObserver observer, Throwable e) {
        observer.onError(Status.INTERNAL
                .withDescription("Something went wrong. Please try again.")
                .augmentDescription("Contact the developer")
                .withCause(e) // This can be attached to the Status locally, but NOT transmitted to the client!
                .asRuntimeException());
    }

    @SuppressWarnings("rawtypes")
    public void generateOnError(StreamObserver observer, Throwable e, String... descriptions) {
        Status status = Status.INTERNAL;
        if (descriptions != null) {
            for (String desc : descriptions) {
                status = status.augmentDescription(desc);
            }
        }
        observer.onError(status.withCause(e).asRuntimeException());
    }
}
