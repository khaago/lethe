package com.khaago.lethe.util;

import com.khaago.lethe.Event;
import com.khaago.lethe.dto.EventDto;
import org.springframework.stereotype.Component;

@Component
public class EventDtoConverter extends AbstractTwoWayConverter<Event, EventDto>{
    @Override
    protected EventDto convert(Event source) {
        return null;
    }

    @Override
    protected Event revert(EventDto target) {
        return null;
    }
}
