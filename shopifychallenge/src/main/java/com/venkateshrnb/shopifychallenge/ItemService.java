package com.venkateshrnb.shopifychallenge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
			throw ex;
		}
	}

	public void deleteItem(String id) {
			itemRepository.deleteById(Integer.parseInt(id));
	}
	
	public Item updateItem(Item item) throws Exception {
		Optional<Item> itemFromDb = itemRepository.findById(item.getId());
		if(!itemFromDb.isPresent()) {
			throw new Exception("Item not present. Please refresh the page for the latest data.");
		}
		try {
			return itemRepository.save(item);
		} catch(Exception ex) {
			throw ex;
		}
}
}
