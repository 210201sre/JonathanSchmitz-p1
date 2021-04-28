package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class CartId implements Serializable {
	
	private long uid;
	//private long iid;
	private Long item;
	
	public CartId() {
		super();
	}
	
	public CartId(long uid, Long iid) {
		super();
		this.uid = uid;
		//this.iid = iid;
		this.item = iid;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(item, uid);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CartId)) {
			return false;
		}
		CartId other = (CartId) obj;
		return item == other.item && uid == other.uid;
	}
	
	

}
