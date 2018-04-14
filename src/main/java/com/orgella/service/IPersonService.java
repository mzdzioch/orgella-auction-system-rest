package com.orgella.service;


import com.orgella.model.Person;
import com.orgella.model.dto.PersonDto;

import java.util.Optional;

public interface IPersonService {


    Optional<Person> tryRegister(PersonDto personDto);

    Optional<Person> tryLogin(PersonDto personDto);
}
