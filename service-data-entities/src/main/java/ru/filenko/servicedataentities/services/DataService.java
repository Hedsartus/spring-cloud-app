package ru.filenko.servicedataentities.services;

import reactor.core.publisher.Flux;
import ru.filenko.servicedataentities.model.EntityData;

import java.util.Map;

public interface DataService {
    Flux<EntityData> getDataEntities(Map<String, String> headers);
}
