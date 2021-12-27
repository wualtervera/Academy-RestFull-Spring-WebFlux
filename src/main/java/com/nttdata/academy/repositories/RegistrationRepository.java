package com.nttdata.academy.repositories;

import com.nttdata.academy.model.dao.RegistrationDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RegistrationRepository extends ReactiveCrudRepository<RegistrationDao, String> {

    Flux<RegistrationDao>findByIdStudent(String idStudent);


}
