package com.khaago.lethe.service;

import com.khaago.lethe.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    public void registerClient(RegistrationRequest registrationRequest) {
        logger.debug("Inside Register Client");
    }
}
