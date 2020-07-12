package com.khaago.lethe.grpc;

import com.khaago.lethe.*;
import com.khaago.lethe.dto.ClientDto;
import com.khaago.lethe.exception.ExceptionService;
import com.khaago.lethe.repo.ClientRepository;
import io.grpc.stub.StreamObserver;
import org.apache.ignite.IgniteAtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.TreeMap;
import java.util.stream.StreamSupport;

/**
 * Manages subscribed clients
 */
@Service
public class ClientService extends BrokerGrpc.BrokerImplBase {

    private ExceptionService exceptionService;
    private ClientRepository clientRepository;
    private ConversionService conversionService;
    private IgniteAtomicLong clientIdGen;

    @Autowired
    public ClientService(ExceptionService exceptionService, ClientRepository clientRepository, ConversionService conversionService, IgniteAtomicLong clientIdGen) {
        this.exceptionService = exceptionService;
        this.clientRepository = clientRepository;
        this.conversionService = conversionService;
        this.clientIdGen = clientIdGen;
    }

    @Override
    public void subscribe(SubscribeOptions options, StreamObserver<Topic> observer) {
        throw new UnsupportedOperationException("Not here bro");
    }

    @Override
    public void register(Client request, StreamObserver<Client> responseObserver) {
        ClientDto clientDto = conversionService.convert(request, ClientDto.class);
        TreeMap<Long, ClientDto> clientEntry = new TreeMap<>();
        long id = clientIdGen.getAndIncrement();
        clientEntry.put(id, clientDto);
        clientRepository.save(clientEntry);
        Client client = conversionService.convert(clientDto, Client.class)
                .toBuilder()
                .setId(id)
                .build();
        responseObserver.onNext(client);
        responseObserver.onCompleted();
    }

    @Override
    public void listen(ListenOptions options, StreamObserver<Event> observer) {
        throw new UnsupportedOperationException("Not here bro");
    }
}
