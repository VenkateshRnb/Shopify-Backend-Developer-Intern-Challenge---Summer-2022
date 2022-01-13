package com.venkateshrnb.shopifychallenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.venkateshrnb.shopifychallenge.pojo.Item;
import com.venkateshrnb.shopifychallenge.repo.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getAllItems(){
		return (List<Item>) itemRepository.findAll();
	}
	
	public Item createItem(Item item){
		try {
			return itemRepository.save(item);
		} catch(Exception ex) {
			return null;
		}
	}

	public void deleteItem(String id) {
			itemRepository.deleteById(Integer.parseInt(id));
	}
}
