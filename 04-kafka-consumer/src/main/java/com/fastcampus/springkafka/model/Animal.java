package com.fastcampus.springkafka.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;

@NoArgsConstructor
@Getter
@ToString
public class Animal {
    private String name;

    @Max(10)
    private int age;

    @JsonCreator
    public Animal(@JsonProperty("name") String name,
                  @JsonProperty("age") int age) {
        this.name = name;
        this.age = age;
    }
}
