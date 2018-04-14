package com.orgella;

import com.orgella.service.AuctionService;
import com.orgella.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrgellaSystemSpringApplication implements CommandLineRunner{

	@Autowired
	AuctionService auctionService;

	@Autowired
    PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(OrgellaSystemSpringApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {


	}
}
