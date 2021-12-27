package com.nttdata.academy.repositories;

import com.nttdata.academy.model.dao.StudentDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends ReactiveCrudRepository<StudentDao, String> {


}
