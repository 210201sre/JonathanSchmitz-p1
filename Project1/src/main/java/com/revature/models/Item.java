package com.revature.models;

import java.util.Date;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data @NoArgsConstructor @AllArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private long iid;
	private String unitname;
	private String description;
	private long quantity;
	private double sellingprice;//seller to customer
	private long totalpurchases;
	
	@Column(insertable = false, updatable = false)
	private Date dateadded;
	private double unitcost; //manufacturer to seller
	//private long mid;

	//========================== HIBERNATE ADDITION ========================================
	@OneToMany()
	private List<Coupon> appliedCoupon;
	//ADD JSON BACKREFERENCE
	@ManyToOne()
	@JoinColumn(name="mid")
	private Manufacturer itemManufacturer;
	//=======================================================================================
	
	public double calcPrice() {
		return this.sellingprice * this.quantity;
	}

	public String toString(boolean b) {
		String nul = "Item [iid=0, unitname=null, description=null, quantity=0, sellingprice=0.0, totalpurchases=0, dateadded=null, unitcost=0.0, mid=0]";
		String str = "Item [iid=" + iid + ", unitname=" + unitname + ", description=" + description + ", quantity="
				+ quantity + ", sellingprice=" + sellingprice + ", totalpurchases=" + totalpurchases + ", dateadded="
				+ dateadded + ", unitCost=" + unitcost + ", mid=" + itemManufacturer + "]";
		if (str.equals(nul)) {
			return null;
		} else {
			return str;
		}
	}
}
