package com.nttdata.academy.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("registration")
public class RegistrationDao implements Serializable {
    @Id
    private String id;
    private String idStudent;
    private String idCourse;
    private LocalDateTime createAt;
}
