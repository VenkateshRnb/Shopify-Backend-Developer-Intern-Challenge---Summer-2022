package com.venkateshrnb.shopifychallenge.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public String getItemById(@PathVariable String id) {
		return "Item: "+id;
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
}
