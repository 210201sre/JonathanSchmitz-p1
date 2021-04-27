package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class CartId implements Serializable {
	
	private long uid;
	//private long iid;
	private long i;
	
	public CartId() {
		super();
	}
	
	public CartId(long uid, long iid) {
		super();
		this.uid = uid;
		//this.iid = iid;
		this.i = iid;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(i, uid);
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
		return i == other.i && uid == other.uid;
	}
	
	

}
