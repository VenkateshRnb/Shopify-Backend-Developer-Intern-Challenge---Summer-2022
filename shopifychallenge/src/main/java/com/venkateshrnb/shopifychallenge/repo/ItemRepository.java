package com.venkateshrnb.shopifychallenge.repo;

import org.springframework.data.repository.CrudRepository;

import com.venkateshrnb.shopifychallenge.pojo.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

}
