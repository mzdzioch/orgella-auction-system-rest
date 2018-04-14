package com.orgella.controller;

import com.orgella.model.Person;
import com.orgella.model.dto.PersonDto;
import com.orgella.model.response.ResponseMessage;
import com.orgella.model.response.StatusResponse;
import com.orgella.service.IPersonService;
import com.orgella.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping(path="/user/")
@CrossOrigin
public class UserController {

    @Autowired
    IPersonService personService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseMessage<Person> registerUser(@RequestBody PersonDto personDto){

        Optional<Person> registrationResult = personService.tryRegister(personDto);

        if(registrationResult.isPresent()){
            return new ResponseMessage<>(StatusResponse.OK, null, registrationResult.get());
        }

        return new ResponseMessage<>(StatusResponse.REQUEST_ERROR, "Unable to register.", null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseMessage<Person> login(@RequestBody PersonDto personDto){

        Optional<Person> login = personService.tryLogin(personDto);

        if(login.isPresent()){
            return new ResponseMessage<>(StatusResponse.OK, null, login.get());
        }
        return new ResponseMessage<>(StatusResponse.REQUEST_ERROR, "Invalid user or password.", null);
    }


}
