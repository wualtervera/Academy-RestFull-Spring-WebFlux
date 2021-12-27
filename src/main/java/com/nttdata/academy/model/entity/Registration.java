package com.nttdata.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    private String id;
    private String idStudent;
    private String idCourse;
    private LocalDateTime createAt;
}
