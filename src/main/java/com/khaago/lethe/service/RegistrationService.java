package com.khaago.lethe.service;

import com.khaago.lethe.RegistrationRequest;
import com.khaago.lethe.dto.Client;
import com.khaago.lethe.repo.ClientRepository;
import org.apache.ignite.IgniteAtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class RegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    IgniteAtomicLong clientIdGen;
    @Autowired
    ClientRepository clientRepository;

    public void registerClient(RegistrationRequest registrationRequest) {
        TreeMap<Long, Client> clientTreeMap = new TreeMap<>();
        Client client = new Client(clientIdGen.getAndIncrement(), registrationRequest.getClientName());
        clientTreeMap.put(clientIdGen.getAndIncrement(), client);
        clientRepository.save(clientTreeMap);
        logger.debug("Saved clients {}", clientTreeMap);
    }
}
