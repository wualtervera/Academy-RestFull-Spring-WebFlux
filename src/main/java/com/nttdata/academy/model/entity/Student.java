package com.nttdata.academy.model.entity;

import com.nttdata.academy.model.base.Person;


public class Student  extends Person {
    public Student(String id, String nombre, int edad) {
        super(id, nombre, edad);
    }

    public Student() {
    }
}
