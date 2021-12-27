package com.nttdata.academy.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface IOperations {
    public Mono<ServerResponse> findAll(ServerRequest request);

    public Mono<ServerResponse> findById(ServerRequest request);

    public Mono<ServerResponse> save(ServerRequest request);

    public Mono<ServerResponse> update(ServerRequest request);

    public Mono<ServerResponse> delete(ServerRequest request);
}
