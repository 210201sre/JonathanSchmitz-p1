package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uid", insertable = false, updatable = false)
	private Long uid = null;
	
	
	private String fname = "";
	private String lname = "";
	
	@Column(unique=true)
	private String uname;
	private String pswrd;
	private String email = null;
	private String phonenum = "";
	private String address = "";
	private String city = "";
	private String state = "";
	private String zip = "";
	private String accesslevel;
	private Long sid;
	
	//========================== HIBERNATE ADDITION ========================================
//	TODO: REMOVE ALL OF THE FOLLOWING, will speed up request time significantly
	//@OneToMany(mappedBy="uid")
	//private List<Cart> myCart;
	//@OneToMany(mappedBy="uid")
	//private List<Backorder> myBackorders;
	//@OneToMany(mappedBy="uid")
	//private List<Transaction> myTransactions;
	//=======================================================================================

	//specially designed toString() to identify new Objects
	public String toString(boolean y) {
		String nul = "User [uid=0, fname=, lname=, uname=null, pswrd=null, email=null, phonenum=, address=, city=, state=, zip=, accesslevel=null, sid=null]";
		String str = "User [uid=" + uid + ", fname=" + fname + ", lname=" + lname + ", uname=" + uname + ", pswrd="
				+ pswrd + ", email=" + email + ", phonenum=" + phonenum + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", accesslevel=" + accesslevel + ", sid=" + sid + "]";
		if (str.equals(nul)) {
			return null;
		} else {
			return str;
		}
	}

	public User(long uid2) {
		this.uid=uid2;
		// TODO Auto-generated constructor stub
	}
	
}
