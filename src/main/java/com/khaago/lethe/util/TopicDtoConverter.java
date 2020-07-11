package com.khaago.lethe.util;

import com.khaago.lethe.Client;
import com.khaago.lethe.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class TopicDtoConverter extends AbstractTwoWayConverter<Client, ClientDto> {
    @Override
    protected ClientDto convert(Client source) {
        return null;
    }

    @Override
    protected Client revert(ClientDto target) {
        return null;
    }
}
