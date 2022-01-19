package com.venkateshrnb.shopifychallenge;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.venkateshrnb.shopifychallenge.pojo.Item;
import com.venkateshrnb.shopifychallenge.repo.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	final String CSV_LOCATION = "Item.csv";

	public List<Item> getAllItems() {
		return (List<Item>) itemRepository.findAll();
	}

	public Item findItemByID(String id) {
		Optional<Item> item = itemRepository.findById(Integer.parseInt(id));
		if (item.isPresent()) {
			return item.get();
		} else {
			return null;
		}

	}

	public Item createItem(Item item) {
		try {
			return itemRepository.save(item);
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void deleteItem(String id) {
		itemRepository.deleteById(Integer.parseInt(id));
	}

	public Item updateItem(Item item) throws Exception {
		Optional<Item> itemFromDb = itemRepository.findById(item.getId());
		if (!itemFromDb.isPresent()) {
			throw new Exception("Item not present. Please refresh the page for the latest data.");
		}
		try {
			return itemRepository.save(item);
		} catch (Exception ex) {
			throw ex;
		}
	}

	public File prepareNewCSV(Item item) throws StreamWriteException, DatabindException, IOException {
		String fileName = "item_"+item.getItemName()+".csv";
        fileName = fileName.replaceAll("\\s", "");
		File csvOutputFile = new File(fileName);
		CsvMapper mapper = new CsvMapper();
		
		CsvSchema schema1 = CsvSchema.builder().setUseHeader(true).addColumn("id").addColumn("itemName")
				.addColumn("itemType").addColumn("itemCount").addColumn("itemSellingPrice")
				.addColumn("itemCostPrice").addColumn("itemSupplierId").addColumn("itemSupplierName").build();
		ObjectWriter writer = mapper.writerFor(Item.class).with(schema1);

		writer.writeValue(csvOutputFile, item);

		System.out.println("Users saved to csv file under path: ");
		System.out.println(csvOutputFile);
		return csvOutputFile;
	}
}
