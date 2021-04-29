package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Item;
import com.revature.models.Manufacturer;

public interface ItemDAO extends JpaRepository<Item, Long> {
	
	//@Query(value = "SELECT * FROM projectzero.items WHERE mid = :mid")
	public List<Item> findAllByMid(long mid);
	
	//@Modifying
	//@Query(value = "DELETE FROM projectzero.items WHERE mid = :mid")
	public void deleteByMid(long mid);

}
