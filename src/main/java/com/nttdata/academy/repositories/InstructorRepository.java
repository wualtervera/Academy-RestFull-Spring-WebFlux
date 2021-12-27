package com.nttdata.academy.repositories;

import com.nttdata.academy.model.dao.InstructorDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends ReactiveCrudRepository<InstructorDao, String> {
}
