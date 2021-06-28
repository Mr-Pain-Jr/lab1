/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacos.springmongo.transferData;

import com.example.relationaldataaccess.RelationalDataAccessApplication;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

/**
 *
 * @author User
 */
public class ProductTransfer {
    
	private static final Logger log = LoggerFactory.getLogger(RelationalDataAccessApplication.class);

	public static void main(String args[]) {
		SpringApplication.run(RelationalDataAccessApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE products IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE products(" +
				"name VARCHAR(255), desc VARCHAR(255), price INT)");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("Pinko bag", "D&G bag").stream()
				.map(name -> name.split(" "))
				.collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		log.info("Querying for customer records where first_name = 'Josh':");
		jdbcTemplate.query(
				"SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
				(rs, rowNum) -> new Products(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
		).forEach(customer -> log.info(customer.toString()));
	}
    
}
