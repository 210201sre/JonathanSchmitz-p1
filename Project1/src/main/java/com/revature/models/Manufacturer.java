package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manufacturers")
@Data @NoArgsConstructor @AllArgsConstructor
public class Manufacturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long mid;
	private String mname;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String email = null;
	private String phonenum;
	private String representative = null;
	
	//========================== HIBERNATE ADDITION ========================================
	//@OneToMany()
	//@JsonBackReference
	//private List<Item> manufacturersItems;
	
	public Manufacturer(long m) {
		this.mid = m;
	}
	//=======================================================================================
	
	public String toString(boolean b) {
		String nul = "Manufacturer [mid=0, mname=null, address=null, city=null, state=null, zip=null, email=null, phonenum=null, representative=null]";
		String str = "Manufacturer [mid=" + mid + ", mname=" + mname + ", address=" + address + ", city=" + city + ", state="
				+ state + ", zip=" + zip + ", email=" + email + ", phonenum=" + phonenum + ", representative=" + representative + "]";
		if (str.equals(nul)) {
			return null;
		} else {
			return str;
		}
	}
}
