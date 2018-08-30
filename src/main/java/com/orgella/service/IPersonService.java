package com.orgella.service;


import com.orgella.model.Person;
import com.orgella.model.dto.PersonDto;

import java.util.Optional;

public interface IPersonService {

    Person savePerson(Person person);

    void deletePerson(Person person);

    boolean isLoginAndPasswordCorrect(Person person);

    Optional<Person> findPersonByLogin(String login);

    Optional<Person> tryRegister(PersonDto personDto);

    Optional<Person> tryLogin(PersonDto personDto);

}
