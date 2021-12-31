package com.nttdata.academy.handler;

import com.nttdata.academy.model.entity.Instructor;
import com.nttdata.academy.services.InstructorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
@DirtiesContext
class InstructorHandlerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private InstructorService service;

    String uri = "/api/v1/instructor";
    Instructor s1 = new Instructor("61c239bc4db0233475524632", "Carlos", 30);
    Instructor s2 = new Instructor("61c23a0f4db0233475524633", "Juan Carlos", 50);
    Instructor s3 = new Instructor("61c1f78cdb38643ebb104a8c", "Mark", 36);

    @Test
    void findAll() {
        Flux<Instructor> instructorFlux = Flux.just(s1, s2, s3);

        when(this.service.findAll()).thenReturn(instructorFlux);

        Flux<Instructor> instructorFluxWeb = webTestClient.get().uri(uri)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Instructor.class)
                .getResponseBody();

        StepVerifier.create(instructorFluxWeb)
                .expectSubscription()
                .expectNext(s1)
                .expectNext(s2)
                .expectNext(s3)
                .verifyComplete();
    }

    @Test
    void findById() {
        Mono<Instructor> instructorMono = Mono.just(s1);

        when(this.service.findById(any())).thenReturn(instructorMono);

        Flux<Instructor> instructortMonoWeb = webTestClient.get().uri(uri.concat("/{id}"), s1.getId())
                .exchange()
                .expectStatus().isOk()
                .returnResult(Instructor.class)
                .getResponseBody();

        StepVerifier.create(instructortMonoWeb)
                .expectSubscription()
                .expectNextMatches(i -> i.getNombre().equals("Carlos"))
                .verifyComplete();
    }

    @Test
    void save() {
        Mono<Instructor> instructorMono = Mono.just(s1);
        when(this.service.save(s1)).thenReturn(instructorMono);

        webTestClient.post().uri(uri)
                .body(Mono.just(s1), Instructor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void update() {
        /*Mono<Instructor> instructorMono = Mono.just(s1);

        when(this.service.update(s1.getId(), s1)).thenReturn(instructorMono);

        webTestClient.put().uri(uri.concat("/{id}"), s1.getId())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(s1), Instructor.class)
                .exchange()
                .expectStatus().isCreated();*/
    }

    @Test
    void delete() {
        /*Mono<Void> voidReturn = Mono.empty();
        when(this.service.delete(s1.getId())).thenReturn(voidReturn);

        webTestClient.delete().uri(uri.concat("/{id}"), s1.getId())
                .exchange()
                .expectStatus().isNoContent();*/
    }
}