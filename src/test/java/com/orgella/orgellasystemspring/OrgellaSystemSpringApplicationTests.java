package com.orgella.orgellasystemspring;

import com.orgella.service.AuctionService;
import com.orgella.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgellaSystemSpringApplicationTests {

	@Autowired
	AuctionService auctionService;

	@Autowired
    PersonService personService;

	@Test
	public void contextLoads() {
	}

}
