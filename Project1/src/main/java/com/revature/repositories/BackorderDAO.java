package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Backorder;

public interface BackorderDAO extends JpaRepository<Backorder, Long> {
	
	// find out what the query is that returns a boolean
	boolean existsByIid(long iid);

	//@Modifying
	//@Query(value = "DELETE FROM projectzero.backorders WHERE uid = :uid")
	void deleteByUid(Long uid);
	
	//@Query(value = "SELECT * FROM projectzero.backorders WHERE cid = :cid")
	public List<Backorder> findAllByCid(long cid);

	public List<Backorder> findAllByUid(long uid);
}
