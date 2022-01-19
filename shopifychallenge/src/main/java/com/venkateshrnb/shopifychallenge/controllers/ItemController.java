package com.venkateshrnb.shopifychallenge.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.venkateshrnb.shopifychallenge.ItemService;
import com.venkateshrnb.shopifychallenge.pojo.Item;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
    private static Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@GetMapping("/home")
	public String welcomePage() {
		return "Welcome to Shopify";
	}
	
	@GetMapping("/items")
	public List<Item> getAllItems() {
		List<Item> list = itemService.getAllItems();
		return list;
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable String id) {
		Item item = itemService.findItemByID(id);
		if(item == null) {
			logger.error("Item not present with the id:",id);
				return new ResponseEntity<Item>(null, null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(item, null, HttpStatus.OK);
	}
	
	@PostMapping("/items/")
	public ResponseEntity<HttpStatus> createItem(@RequestBody Item item) {
		try {
			itemService.createItem(item);
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);		
	}
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable String id) {
		
		try {
			itemService.deleteItem(id);
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PutMapping("/items/{id}")
	public ResponseEntity<HttpStatus> updateItem(@RequestBody Item item) {
		try {
			itemService.updateItem(item);
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			if(ex.getMessage().equalsIgnoreCase("Item not present. Please refresh the page for the latest data.")) {
				return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping("/items/getCsv/{id}")
	public ResponseEntity<byte[]> getItemCSVById(@PathVariable String id) throws CsvDataTypeMismatchException {
		Item item = itemService.findItemByID(id);
		File file = null;
		if(item == null) {
			logger.error("Item not present with the id:",id);
				return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
		
		try {
			file = itemService.prepareNewCSV(item);
			byte[] contents = Files.readAllBytes(file.toPath());

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentDispositionFormData(file.getName(), file.getName());
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		    return response;
		} catch (IOException e) {
			logger.error("Error while preparing CSV for the id:",id);
			e.printStackTrace();
		}
		return null;
		
	}

	
}
