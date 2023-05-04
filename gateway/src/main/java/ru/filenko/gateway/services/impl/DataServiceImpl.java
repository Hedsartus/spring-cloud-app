package ru.filenko.gateway.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.filenko.gateway.model.EntityData;
import ru.filenko.gateway.repositories.EntityRepository;
import ru.filenko.gateway.services.DataService;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final EntityRepository entityRepository;
    @Override
    public Flux<EntityData> getEntityDataByIdUser(long idUser) {
        return entityRepository.getEntityDataByIdUser(idUser);
    }
}
