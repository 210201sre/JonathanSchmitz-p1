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
@Table(name = "cart")
@IdClass(CartId.class)
@Data @NoArgsConstructor //@AllArgsConstructor
public class Cart implements Serializable {

	//uid equivalent to transaction id when requesting items for a transaction
//	TODO: COMMENT THE FOLLOWING LINE, UPDATE CartId.java
	//@Id
	//@Column(name="uid")
	@Column(insertable=false, updatable=false)
	private long uid;
	//@Id
	//@Column(name="iid")
	//private long iid;
	@Column(insertable=false, updatable=false)
	private long iid;
	
	private long quantity; // also swap FindTuiByTid in CartDAO
	@Column(name = "cid")
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
	private Item item;
	
	public Cart(long uid, long iid, long quantity, Long cid, User user, Item item) {
		super();
		this.uid = uid;
		this.iid = iid;
		this.quantity = quantity;
		this.cid = cid;
		//this.user = user;
		this.user=new User(uid);
		//this.item = item;
		this.item=new Item(iid);
	}
	//=======================================================================================
	
	public String toString(boolean b) {
		String nul = "CartItemProto [uid=0, quantity=0, cid=0, i=0]";
		String str = "CartItemProto [uid=" + uid + ", quantity=" + quantity + ", cid=" + cid + ", i=" + iid + "]";
		if (str.equals(nul)) {
			return null;
		} else {
			return str;
		}
	}



//	public String toString(boolean b) {
//		String nul = "CartItem [uid=0, cartQuantity=0, cid=0, i=0]";
//		String str = "CartItem [uid=" + uid + ", cartQuantity=" + cartQuantity + ", cid=" + cid + ", i=" + iid + "]";
//		if (str.equals(nul)) {
//			return null;
//		} else {
//			return str;
//		}
//	}
}
