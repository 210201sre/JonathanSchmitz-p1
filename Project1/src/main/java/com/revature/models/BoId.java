package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

public class BoId implements Serializable {

//	private long uid;
	private long user;
	//private long iid;
	private long backOrderItem;
	
	public BoId() {
		super();
	}
	
	public BoId(long uid, long iid) {
		super();
//		this.uid = uid;
		this.user = uid;
		//this.iid = iid;
		this.backOrderItem=iid;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(backOrderItem, user);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BoId)) {
			return false;
		}
		BoId other = (BoId) obj;
		return backOrderItem == other.backOrderItem && user == other.user;
	}
	
	
}
