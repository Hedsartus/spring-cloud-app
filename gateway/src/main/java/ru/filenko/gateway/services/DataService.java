package ru.filenko.gateway.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.filenko.gateway.model.EntityData;

public interface DataService {
    Flux<EntityData> getEntityDataByIdUser(long idUser);
    Mono<EntityData> addEntityData(long idUser, String title);
}
