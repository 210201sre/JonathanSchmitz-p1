package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "backorders")
@IdClass(BoId.class)
@Data @NoArgsConstructor //@AllArgsConstructor
public class Backorder implements Serializable {

	//uid equivalent to transaction id when requesting items for a transaction
//	TODO: COMMENT THE FOLLOWING LINE, UPDATE BoId.java
	//@Id
	//private long uid;
	@Column(insertable=false, updatable=false)
	private long uid;
	//@Id
	//private long iid;
	@Column(insertable=false, updatable=false)
	private long iid;
	
	private long quantity; // also swap FindTuiByTid in CartDAO
	private long cid;
	
	//========================== HIBERNATE ADDITION ========================================
//	TODO: CREATE @ManyToOne() for User user;
	@Id
	@ManyToOne()
	@JoinColumn(name="uid")
	private User user;
	
	@Id
	@ManyToOne()
	@JoinColumn(name="iid")
	private Item backOrderItem;
	
	public Backorder(long uid, long iid, long quantity, long cid, User user, Item backOrderItem) {
		super();
		this.uid = uid;
		this.iid = iid;
		this.quantity = quantity;
		this.cid = cid;
		//this.user = user;
		this.user=new User(uid);
		this.backOrderItem = backOrderItem;
	}
	//=======================================================================================
	
	public String toString(boolean b) {
		String nul = "BackorderProto [uid=0, quantity=0, cid=0, i=0]";
		String str = "BackorderProto [uid=" + user + ", quantity=" + quantity + ", cid=" + cid + ", i=" + backOrderItem + "]";
		if (str.equals(nul)) {
			return null;
		} else {
			return str;
		}
	}
}
