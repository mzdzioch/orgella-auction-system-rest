package com.orgella;

import com.orgella.model.Category;
import com.orgella.service.AuctionService;
import com.orgella.service.CategoryService;
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

	@Autowired
	CategoryService categoryService;

	public static void main(String[] args) {

		SpringApplication.run(OrgellaSystemSpringApplication.class, args);

	}

	@Override
	public void run(String... strings) throws Exception {

		categoryService.save(new Category("ROOT", null));
		categoryService.save(new Category("Electronics", categoryService.findCategoryByName("ROOT").get()));
		categoryService.save(new Category("Motors", categoryService.findCategoryByName("ROOT").get()));
		categoryService.save(new Category("Clothes", categoryService.findCategoryByName("ROOT").get()));

		categoryService.save(new Category("Laptops", categoryService.findCategoryByName("Electronics").get()));
		categoryService.save(new Category("PC", categoryService.findCategoryByName("Electronics").get()));
		categoryService.save(new Category("Servers", categoryService.findCategoryByName("Electronics").get()));
		//adding subcategories for Motors category
		categoryService.save(new Category("Parts", categoryService.findCategoryByName("Motors").get()));
		categoryService.save(new Category("Cars", categoryService.findCategoryByName("Motors").get()));
		categoryService.save(new Category("Vehicles", categoryService.findCategoryByName("Motors").get()));
		categoryService.save(new Category("Trucks", categoryService.findCategoryByName("Motors").get()));
		//adding subcategories for Clothes category
		categoryService.save(new Category("Women Clothing", categoryService.findCategoryByName("Clothes").get()));
		categoryService.save(new Category("Men Clothing", categoryService.findCategoryByName("Clothes").get()));
		categoryService.save(new Category("Shoes", categoryService.findCategoryByName("Clothes").get()));

		categoryService.save(new Category("House", categoryService.findCategoryByName("ROOT").get()));

	}
}
