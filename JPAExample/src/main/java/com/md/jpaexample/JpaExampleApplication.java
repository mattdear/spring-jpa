package com.md.jpaexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaExampleApplication {

    private static final Logger log = LoggerFactory.getLogger(JpaExampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            //Add customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Matthew", "Dear"));
            repository.save(new Customer("Amy", "Dear"));
            repository.save(new Customer("Fred", "Baker"));

            //Fetch all customers
            log.info("Customers fetched with findAll():");
            log.info("---------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            //Fetch customer by id
            Customer customer = repository.findById(1L);
            log.info("Customers found with findById(1L):");
            log.info("---------------------------------");
            log.info(customer.toString());
            log.info("");

            //Fetch customers by last name
            log.info("Customers found with findByLastName('Dear'):");
            log.info("---------------------------------");
            repository.findByLastName("Dear").forEach(dear -> {
                log.info(dear.toString());
            });
            log.info("");

            //Fetch customers by first name
            log.info("Customers found with findByFirstName('Fred'):");
            log.info("---------------------------------");
            repository.findByFirstName("Fred").forEach(fred -> {
                log.info(fred.toString());
            });
            log.info("");
        };
    }
}
