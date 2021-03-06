package com.venkateshrnb.shopifychallenge.pojo;

import java.sql.Blob;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String itemName;

	private String itemType;

//	@JsonProperty("Item Image")
//    private Blob itemImage;

	private long itemCount;

	private long itemSellingPrice;

	private long itemCostPrice;

	private long itemSupplierId;

	private String itemSupplierName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

//	public Blob getItemImage() {
//		return itemImage;
//	}
//
//	public void setItemImage(Blob itemImage) {
//		this.itemImage = itemImage;
//	}

	public long getItemCount() {
		return itemCount;
	}

	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}

	public long getItemSellingPrice() {
		return itemSellingPrice;
	}

	public void setItemSellingPrice(long itemSellingPrice) {
		this.itemSellingPrice = itemSellingPrice;
	}

	public long getItemCostPrice() {
		return itemCostPrice;
	}

	public void setItemCostPrice(long itemCostPrice) {
		this.itemCostPrice = itemCostPrice;
	}

	public long getItemSupplierId() {
		return itemSupplierId;
	}

	public void setItemSupplierId(long itemSupplierId) {
		this.itemSupplierId = itemSupplierId;
	}

	public String getItemSupplierName() {
		return itemSupplierName;
	}

	public void setItemSupplierName(String itemSupplierName) {
		this.itemSupplierName = itemSupplierName;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", itemType=" + itemType + ", itemCount=" + itemCount
				+ ", itemSellingPrice=" + itemSellingPrice + ", itemCostPrice=" + itemCostPrice + ", itemSupplierId="
				+ itemSupplierId + ", itemSupplierName=" + itemSupplierName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemCostPrice, itemCount, itemName, itemSellingPrice, itemSupplierId, itemSupplierName,
				itemType);
	}

	

}
