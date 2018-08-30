package com.orgella.service;

import com.orgella.model.Person;
import com.orgella.model.dto.PersonDto;
import com.orgella.model.factory.PersonFactory;
import com.orgella.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
public class PersonService implements IPersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person savePerson(Person person) {

        try {
            return personRepository.save(person);

        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    @Override
    public boolean isLoginAndPasswordCorrect(Person person) {

        Optional<Person> findPerson = findPersonByLogin(person.getLogin());

        if (findPerson == null) {
            return false;
        } else if (findPerson.get().getPassword().equals(person.getPassword())) {
            return true;
        }

        return false;
    }

    @Override
    public Optional<Person> findPersonByLogin(String login) {

        return personRepository.findPersonByLogin(login);
    }

    public Boolean existPersonByLogin(String login) {

        return personRepository.existsPersonByLogin(login);
    }


    @Override
    public Optional<Person> tryRegister(PersonDto personDto) {

        Optional<Person> person = personRepository.findPersonByLogin(personDto.getLogin());

        if(person != null){
            return Optional.empty();
        } else {
            Person user = personRepository.save(PersonFactory.createPerson(personDto));
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<Person> tryLogin(PersonDto personDto) {
        Person person = personRepository.findPersonByLoginAndPassword(
                personDto.getLogin(), personDto.getPasswordHash());
        return Optional.ofNullable(person);
    }
}
