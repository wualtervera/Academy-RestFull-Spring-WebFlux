package com.nttdata.academy.repositories;

import com.nttdata.academy.model.dao.InstructorCourseDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorCourseRepository extends ReactiveCrudRepository<InstructorCourseDao, String> {
}
