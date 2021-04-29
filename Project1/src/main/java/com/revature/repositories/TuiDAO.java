package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Coupon;
import com.revature.models.Item;
import com.revature.models.TUI;
import com.revature.models.Transaction;

public interface TuiDAO extends JpaRepository<TUI, Long> {

	//@Query(value = "SELECT * FROM projectzero.t_u_i WHERE tid = :tid")
	public List<TUI> findAllByTid(long tid);

	//@Modifying
	//@Query(value = "DELETE FROM projectzero.t_u_i WHERE tid = :tid")
	public void deleteByTid(long tid);

	//@Modifying
	//@Query(value = "DELETE FROM projectzero.t_u_i WHERE tid = :tid AND iid = :iid")
	public void deleteByTidAndIid(long tid, long iid);

	// find out what the query is that returns a boolean
	public boolean existsByIid(long iid);
	
	//@Query(value = "SELECT * FROM projectzero.t_u_i WHERE cid = :cid")
	public List<TUI> findAllByCid(long cid);
	
//	@Query(value = "SELECT tid AS uid, iid, quantity, cid FROM projectzero.t_u_i WHERE tid = :tid")
//	public List<CartItemProto> findTuiByTid(long tid);	
	
//	public List<TuiProto> findByTid(long tid);
}
