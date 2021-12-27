package com.nttdata.academy.repositories;

import com.nttdata.academy.model.dao.ChapterDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends ReactiveCrudRepository<ChapterDao, String> {
}
