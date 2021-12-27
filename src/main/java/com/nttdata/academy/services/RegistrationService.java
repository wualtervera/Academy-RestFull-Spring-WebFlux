package com.nttdata.academy.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.academy.model.dao.RegistrationDao;
import com.nttdata.academy.model.entity.Registration;
import com.nttdata.academy.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class RegistrationService implements IOpereationServices<Registration> {

    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Flux<Registration> findAll() {
        return this.repository.findAll().map(this::toRegistration);
    }

    @Override
    public Mono<Registration> findById(String id) {
        return this.repository.findById(id).map(this::toRegistration);
    }

    @Override
    public Mono<Registration> save(Registration registration) {
        registration.setCreateAt(LocalDateTime.now());
        return this.repository.save(this.toRegistrationDao(registration)).map(this::toRegistration);
    }

    @Override
    public Mono<Registration> update(String id, Registration registration) {
        registration.setId(id);
        registration.setCreateAt(LocalDateTime.now());
        return this.repository.save(this.toRegistrationDao(registration)).map(this::toRegistration);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }

    public Flux<Registration> findByIdStudent(String idStudent) {
        return this.repository.findByIdStudent(idStudent).map(this::toRegistration);


    }


    public Registration toRegistration(RegistrationDao registrationDao) {
        return this.mapper.convertValue(registrationDao, Registration.class);
    }

    public RegistrationDao toRegistrationDao(Registration registration) {
        return this.mapper.convertValue(registration, RegistrationDao.class);
    }

}
