package com.khaago.lethe.grpc;

import com.khaago.lethe.BrokerGrpc;
import com.khaago.lethe.Client;
import com.khaago.lethe.SubscribeOptions;
import com.khaago.lethe.Topic;
import com.khaago.lethe.dto.ClientDto;
import com.khaago.lethe.exception.ExceptionService;
import com.khaago.lethe.exception.InvalidInputException;
import com.khaago.lethe.repo.ClientRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.apache.ignite.IgniteAtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.TreeMap;

/**
 * Manages subscribed clients
 */
@Service
public class ClientService extends BrokerGrpc.BrokerImplBase {

    private static final String NOT_HERE_BRO = "Not here bro";
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
        throw new UnsupportedOperationException(NOT_HERE_BRO);
    }

    @Override
    public void getClient(Client request, StreamObserver<Client> responseObserver) {
        Cache.Entry<Long, ClientDto> entry = clientRepository.findTopByNameLike(request.getName());
        ClientDto clientDto = null;
        clientDto = entry.getValue();
        if (clientDto == null) {
            exceptionService.generateOnError(responseObserver, new InvalidInputException(), Status.NOT_FOUND);
        }
        Client client = conversionService.convert(clientDto, Client.class);
        responseObserver.onNext(client);
        responseObserver.onCompleted();
    }

    @Override
    public void update(Client request, StreamObserver<Client> responseObserver) {
        ClientDto clientEntry = clientRepository.findById(request.getId()).orElse(null);
        if (clientEntry == null) {
            exceptionService.generateOnError(responseObserver, new InvalidInputException(), Status.NOT_FOUND);
        }
        ClientDto clientDto = conversionService.convert(clientEntry, ClientDto.class);
        if (clientDto == null) {
            exceptionService.generateOnError(responseObserver, new InvalidInputException(), Status.NOT_FOUND);
        }
        clientRepository.save(clientDto);
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void register(Client request, StreamObserver<Client> responseObserver) {
        ClientDto clientDto = conversionService.convert(request, ClientDto.class);
        if (clientDto == null) {
            exceptionService.generateOnError(responseObserver, new InvalidInputException(), Status.INVALID_ARGUMENT);
        }
        TreeMap<Long, ClientDto> clientEntry = new TreeMap<>();
        long id = clientIdGen.getAndIncrement();
        clientDto.setId(id);
        clientEntry.put(id, clientDto);
        clientRepository.save(clientEntry);
        request = request.toBuilder().setId(id).build();
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }
}