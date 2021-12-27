package com.nttdata.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    private String id;
    private String title;
    private String content;
    private String idCourse;
    private LocalDateTime createAt;
}
