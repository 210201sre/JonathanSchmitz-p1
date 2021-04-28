package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Coupon;
import com.revature.models.Item;
import com.revature.models.TUI;
import com.revature.models.Transaction;

public interface TuiDAO extends JpaRepository<TUI, Long> {

	//@Query(value = "SELECT * FROM projectzero.t_u_i WHERE tid = :tid")
	public List<TUI> findAllByTransaction(Transaction t);

	//@Modifying
	//@Query(value = "DELETE FROM projectzero.t_u_i WHERE tid = :tid")
	public void deleteByTransaction(Transaction t);

	//@Modifying
	//@Query(value = "DELETE FROM projectzero.t_u_i WHERE tid = :tid AND iid = :iid")
	public void deleteByTransactionAndItem(Transaction t, Item i);

	// find out what the query is that returns a boolean
	public boolean existsByItem(Item i);
	
	//@Query(value = "SELECT * FROM projectzero.t_u_i WHERE cid = :cid")
	public List<TUI> findAllByCoupon(Coupon c);
	
//	@Query(value = "SELECT tid AS uid, iid, quantity, cid FROM projectzero.t_u_i WHERE tid = :tid")
//	public List<CartItemProto> findTuiByTid(long tid);	
	
//	public List<TuiProto> findByTid(long tid);
}
