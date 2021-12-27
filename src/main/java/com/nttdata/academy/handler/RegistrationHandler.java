package com.nttdata.academy.handler;

import com.nttdata.academy.model.entity.Registration;
import com.nttdata.academy.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RegistrationHandler implements IOperations {

    @Autowired
    private RegistrationService service;


    @Override
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.service.findAll(), Registration.class);
    }

    @Override
    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.service.findById(id)
                .flatMap(registration -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(registration)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Registration> registrationMono = request.bodyToMono(Registration.class);

        return registrationMono.flatMap(registration -> this.service.save(registration)
                .flatMap(registrationdb -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(registrationdb)))
                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Registration> registrationMono = request.bodyToMono(Registration.class);

        return this.service.findById(id)
                .flatMap(r -> registrationMono
                        .flatMap(registration -> this.service.update(id, registration)
                                .flatMap(registrationdb -> ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(registrationdb))))
                        .switchIfEmpty(ServerResponse.notFound().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.service.findById(id)
                .flatMap(r -> this.service.delete(id).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> findByIdStudent(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .body(this.service.findByIdStudent(id), Registration.class);
    }
}
