package com.nttdata.academy.model.dao;

import com.nttdata.academy.model.base.CourseBase;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("course")
public class CourseDao extends CourseBase implements Serializable {

}
