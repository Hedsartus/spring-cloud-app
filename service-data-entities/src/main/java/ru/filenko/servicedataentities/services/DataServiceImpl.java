package ru.filenko.servicedataentities.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.filenko.servicedataentities.common.ParserHeaders;
import ru.filenko.servicedataentities.model.EntityData;

import java.util.Map;

@Service
@Slf4j
public class DataServiceImpl implements DataService {
    @Value("${app.connect.adress}")
    private String host;
    private final String COOKIE_SESSION_HEADER = "SESSION";

    @Override
    public Flux<EntityData> getDataEntities(Map<String, String> headers) {
        String sessionCookie = ParserHeaders.getHeaderValue(headers, "Cookie");

        if (sessionCookie != null && !sessionCookie.isBlank()) {
            return WebClient.builder().baseUrl(host).build()
                    .get()
                    .uri("/get-data-entities")
                    .cookies(cookies -> cookies.add(COOKIE_SESSION_HEADER, sessionCookie))
                    .retrieve()
                    .bodyToFlux(EntityData.class);
        }
        return null;
    }
}
