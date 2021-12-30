package com.nttdata.academy.handler;

import com.nttdata.academy.model.entity.Student;
import com.nttdata.academy.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
public class StudenHandlerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StudentService service;

    String uri = "/api/v1/student";
    Student s1 = new Student("61c146f39a746217024a9e59", "Bill Gates", 65);
    Student s2 = new Student("61c147ef9a746217024a9e5a", "Steve Jobs", 60);
    Student s3 = new Student("61c1f78cdb38643ebb104a8c", "Mark Suckemberg", 36);

    @Test
    void findAll() {
        Flux<Student> studentFlux = Flux.just(s1, s2, s3);

        when(this.service.findAll()).thenReturn(studentFlux);

        Flux<Student> studentFluxWeb = webTestClient.get().uri(uri)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Student.class)
                .getResponseBody();

        StepVerifier.create(studentFluxWeb)
                .expectSubscription()
                .expectNext(s1)
                .expectNext(s2)
                .expectNext(s3)
                .verifyComplete();

    }

    @Test
    void findById() {
        Mono<Student> studentMono = Mono.just(s1);

        when(this.service.findById(any())).thenReturn(studentMono);

        Flux<Student> studentMonoWeb = webTestClient.get().uri(uri.concat("/61c146f39a746217024a9e59"))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Student.class)
                .getResponseBody();

        StepVerifier.create(studentMonoWeb)
                .expectSubscription()
                .expectNextMatches(s -> s.getNombre().equals("Bill Gates"))
                .verifyComplete();
    }

    @Test
    void save() {
        Mono<Student> studentMono = Mono.just(s1);
        when(this.service.save(s1)).thenReturn(studentMono);

        webTestClient.post().uri(uri)
                .body(Mono.just(s1), Student.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void update() {

        Mono<Student> studentMono = Mono.just(s1);
        when(this.service.update(s1.getId(), s1)).thenReturn(studentMono);

        webTestClient.put().uri(uri.concat("/{id}"), s1.getId())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(s1), Student.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void delete() {
        //given(this.service.delete(any())).willReturn(Mono.empty());

        Mono<Void> voidReturn = Mono.empty();
        when(this.service.delete(s1.getId())).thenReturn(voidReturn);

        webTestClient.delete().uri(uri.concat("/{id}"), s1.getId())
                .exchange()
                .expectStatus().isNoContent();

    }
}