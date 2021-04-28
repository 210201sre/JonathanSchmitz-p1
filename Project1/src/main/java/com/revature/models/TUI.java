package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_u_i")
@IdClass(TuiId.class)
@Data @NoArgsConstructor @AllArgsConstructor
//public class TUI implements Serializable {
public class TUI {
	//uid equivalent to transaction id when requesting items for a transaction
	//@Id
	//private long tid;
	//@Id
	//private long iid;
	private long quantity; // also swap FindTuiByTid in CartDAO
	//private long cid;
	
	//================================ HIBERNATE ADDITION ============================
	@Id
	@ManyToOne()
	@JoinColumn(name="tid")
	@JsonBackReference
	private Transaction transaction;
	
	@Id
	@ManyToOne()
	@JoinColumn(name="iid")
	private Item item;
	
	@ManyToOne()
	@JoinColumn(name="cid", nullable=true, updatable=false, insertable=false, referencedColumnName="cid")
	private Coupon coupon;
	
	public TUI(Long tid, Long iid, Long cid) {
		this.transaction = new Transaction(tid);
		this.item = new Item(iid);
		this.coupon = new Coupon(cid);
	}
	//================================================================================
	
	public String toString(boolean b) {
		String nul = "TuiProto [tid=0, quantity=0, cid=0, i=0]";
		String str = "TuiProto [tid=" + transaction + ", quantity=" + quantity + ", cid=" + coupon + ", i=" + item + "]";
		if (str.equals(nul)) {
			return null;
		} else {
			return str;
		}
	}
}
