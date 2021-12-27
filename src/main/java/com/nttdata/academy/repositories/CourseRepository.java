package com.nttdata.academy.repositories;

import com.nttdata.academy.model.dao.CourseDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CourseRepository extends ReactiveCrudRepository<CourseDao, String> {
}
