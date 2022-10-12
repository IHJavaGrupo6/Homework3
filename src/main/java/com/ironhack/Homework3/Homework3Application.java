package com.ironhack.Homework3;

import com.ironhack.Homework3.repositories.*;
import com.ironhack.Homework3.utilities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Homework3Application implements CommandLineRunner {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	LeadRepository leadRepository;
	@Autowired
	OpportunityRepository opportunityRepository;
	@Autowired
	SalesRepRepository salesRepRepository;
	public static void main(String[] args) {
		SpringApplication.run(Homework3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu();
		menu.mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
	}
}
