package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data @NoArgsConstructor //@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long tid;
//	TODO: COMMENT FOLLOWING LINE
	//private long uid;
	@Column(insertable=false, updatable=false)
	private long uid;
	//@Column(columnDefinition = "money")
	private double totalcost;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(insertable = false, updatable = false)
	private String stamp;	
	
	//========================== HIBERNATE ADDITION ========================================
//	TODO: CREATE @ManyToOne() for User user;
	@ManyToOne()
	@JoinColumn(name="uid")
	private User user;

	@OneToMany(mappedBy="transaction")
	//@JoinColumn
	@JsonBackReference
	private List<TUI> transactionItems;
	
	public Transaction(long tid) {
		this.tid=tid;
	}

	public Transaction(long tid, long uid, double totalcost, String stamp, User user, List<TUI> transactionItems) {
		super();
		this.tid = tid;
		this.uid = uid;
		this.totalcost = totalcost;
		this.stamp = stamp;
		//this.user = user;
		this.user=new User(uid);
		this.transactionItems = transactionItems;
	}
	//=======================================================================================
	

	public String toString(boolean b) {
		String nul = "Transaction [tid=0, uid=0, totalcost=0.0, stamp=null]";
		String str ="Transaction [tid=" + tid + ", uid=" + user + ", totalcost=" + totalcost + ", stamp=" + stamp + "]"; 
		if(str.equals(nul)) {
			return null;
		} else {
			return str;
		}
	}
}
