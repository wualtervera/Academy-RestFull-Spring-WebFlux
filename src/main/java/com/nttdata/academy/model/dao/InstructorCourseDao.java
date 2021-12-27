package com.nttdata.academy.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("instructorCourse")
public class InstructorCourseDao{
    @Id
    private String id;
    private String idInstructor;
    private String idCourse;
    private LocalDateTime createAt;
}
