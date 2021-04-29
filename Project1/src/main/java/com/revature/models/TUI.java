package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
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
import lombok.ToString;

@Entity
@Table(name = "t_u_i")
@IdClass(TuiId.class)
@ToString(exclude= {"transaction"})
@Data @NoArgsConstructor //@AllArgsConstructor
public class TUI implements Serializable {
//public class TUI {
	//uid equivalent to transaction id when requesting items for a transaction
	//@Id
	//private long tid;
	@Column(insertable=false, updatable=false)
	private long tid;
	
	//@Id
	//private long iid;
	@Column(insertable=false, updatable=false)
	private long iid;
	
	private long quantity; // also swap FindTuiByTid in CartDAO
	//private long cid;
	@Column(insertable=false, updatable=false)
	private Long cid = 0L;
	
	//================================ HIBERNATE ADDITION ============================
	@Id
	@ManyToOne()
	@JoinColumn(name="tid")
	private Transaction transaction;
	
	@Id
	@ManyToOne()
	@JoinColumn(name="iid")
	private Item item;
	
	@ManyToOne()
	@JoinColumn(name="cid", nullable=true, updatable=false, insertable=false, referencedColumnName="cid")
	private Coupon coupon = new Coupon(0);
	
	public TUI(Long tid, Long iid, Long cid) {
		this.transaction = new Transaction(tid);
		this.item = new Item(iid);
		this.coupon = new Coupon(cid);
	}

	public TUI(long tid, long iid, long quantity, Long cid, Transaction transaction, Item item, Coupon coupon) {
		super();
		this.tid = tid;
		this.iid = iid;
		this.quantity = quantity;
		this.cid = cid;
//		this.transaction = transaction;
		this.transaction=new Transaction(tid);
//		this.item = item;
		this.item=new Item(iid);
//		this.coupon = coupon;
		this.coupon=new Coupon(cid);
	}
	//================================================================================
	
	public String toString(boolean b) {
		String nul = "TuiProto [tid=0, quantity=0, cid=0, i=0]";
		String str = "TuiProto [tid=" + tid + ", quantity=" + quantity + ", cid=" + cid + ", i=" + iid + "]";
		if (str.equals(nul)) {
			return null;
		} else {
			return str;
		}
	}
}
