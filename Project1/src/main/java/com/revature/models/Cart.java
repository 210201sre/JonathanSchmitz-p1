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
@Data @NoArgsConstructor @AllArgsConstructor
public class Cart implements Serializable {

	//uid equivalent to transaction id when requesting items for a transaction
	@Id
	@Column(name="uid")
	private long uid;
	//@Id
	//@Column(name="iid")
	//private long iid;
	private long quantity; // also swap FindTuiByTid in CartDAO
	@Column(name = "cid")
	private long cid;

	//========================== HIBERNATE ADDITION ========================================
	@Id
	@ManyToOne()
	@JoinColumn(name="iid")
	private Item item;
	//=======================================================================================
	
	public String toString(boolean b) {
		String nul = "CartItemProto [uid=0, quantity=0, cid=0, i=0]";
		String str = "CartItemProto [uid=" + uid + ", quantity=" + quantity + ", cid=" + cid + ", i=" + item + "]";
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
