package ru.filenko.servicedataentities.services;

import jakarta.annotation.PostConstruct;
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
public class DataService {
    private WebClient client;
    @Value("${app.connect.adress}")
    private String host;
    private final String COOKIE_SESSION = "SESSION";

    @PostConstruct
    private void setUp() {
        client = WebClient.create(host);
    }

    public Flux<EntityData> getDataEntities(Map<String, String> headers, long id) {
        String uriMethod = "/get-data-entities/" + id;
        String sessionCookie = ParserHeaders.getHeaderValue(headers, "Cookie");

        if (sessionCookie != null) {
            return client
                    .get()
                    .uri(uriMethod).cookies(cookies -> cookies.add(COOKIE_SESSION, sessionCookie))
                    .retrieve()
                    .bodyToFlux(EntityData.class);
        }
        return null;
    }
}
