package com.nttdata.academy.model.dao;

import com.nttdata.academy.model.base.Person;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("instructor")
public class InstructorDao extends Person implements Serializable {

}
