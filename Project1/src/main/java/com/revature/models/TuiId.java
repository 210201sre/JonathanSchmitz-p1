package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

public class TuiId implements Serializable {

	//private long tid;
	private Long transaction;
	//private long iid;
	private Long item;
	
	public TuiId() {
		super();
	}
	
	public TuiId(Long tid, Long iid) {
		super();
//		this.tid = tid;
		this.transaction=tid;
		//this.iid = iid;
		this.item=iid;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(item, transaction);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TuiId)) {
			return false;
		}
		TuiId other = (TuiId) obj;
		return item == other.item && transaction == other.transaction;
	}
	
}
