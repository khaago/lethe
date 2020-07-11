package com.khaago.lethe.util;

import com.khaago.lethe.Client;
import com.khaago.lethe.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoConverter extends AbstractTwoWayConverter<Client, ClientDto> {
    @Override
    protected ClientDto convert(Client source) {
        return new ClientDto(source.getName(), source.getAddress(), source.getPropertiesMap());
    }

    @Override
    protected Client revert(ClientDto target) {
        return Client.newBuilder()
                .setName(target.getName())
                .setAddress(target.getAddress())
                .putAllProperties(target.getProperties())
                .setId(target.getId())
                .build();
    }
}
