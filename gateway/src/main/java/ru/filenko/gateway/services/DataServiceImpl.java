package ru.filenko.gateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.filenko.gateway.model.EntityData;
import ru.filenko.gateway.repositories.EntityRepository;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final EntityRepository entityRepository;
    @Override
    public Flux<EntityData> getEntityDataByIdUser(long idUser) {
        return entityRepository.getEntityDataByIdUser(idUser);
    }

    @Override
    public Mono<EntityData> addEntityData(long idUser, String title) {
        EntityData entityData = new EntityData();
        entityData.setIdUser(idUser);
        entityData.setTitle(title);
        return entityRepository.save(entityData);
    }
}
