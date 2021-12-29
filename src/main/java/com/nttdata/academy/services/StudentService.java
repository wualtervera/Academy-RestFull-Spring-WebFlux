package com.nttdata.academy.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.academy.model.dao.StudentDao;
import com.nttdata.academy.model.entity.Student;
import com.nttdata.academy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService implements IOpereationServices<Student> {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Flux<Student> findAll() {
        return this.repository.findAll().map(this::toStudent);
    }

    @Override
    public Mono<Student> findById(String id) {
        return this.repository.findById(id).map(this::toStudent);
    }

    @Override
    public Mono<Student> save(Student student) {
        return this.repository.save(this.toStudenDao(student)).map(this::toStudent);
    }

    @Override
    public Mono<Student> update(String id, Student student) {
        student.setId(id);
        return this.repository.save(this.toStudenDao(student)).map(this::toStudent);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }

    public Student toStudent(StudentDao studentDao) {
        return this.objectMapper.convertValue(studentDao, Student.class);
    }

    public StudentDao toStudenDao(Student student) {
        return this.objectMapper.convertValue(student, StudentDao.class);
    }

}
