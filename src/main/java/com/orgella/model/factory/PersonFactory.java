package com.orgella.model.factory;

import com.orgella.model.Person;
import com.orgella.model.dto.PersonDto;

public class PersonFactory {
    public static Person createPerson(PersonDto dto){
        return new Person(dto.getLogin(), dto.getPasswordHash());
    }
}
