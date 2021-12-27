package com.nttdata.academy.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private String id;
    private String nombre;
    private int edad;

}
