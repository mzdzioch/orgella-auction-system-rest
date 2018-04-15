package com.orgella.repository;

import com.orgella.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    public Person findPersonByLoginAndPassword(String login, String password);

    public Optional<Person> findPersonByLogin(String login);

    public List<Person> getAllBy();

    public Boolean existsPersonByLogin(String login);


}
