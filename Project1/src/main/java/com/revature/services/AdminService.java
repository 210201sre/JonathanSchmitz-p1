package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.InvalidException;
import com.revature.models.Backorder;
import com.revature.models.DEPRECIATEDCartItem;
import com.revature.models.Coupon;
import com.revature.models.Item;
import com.revature.models.Key;
import com.revature.models.Transaction;
import com.revature.models.TUI;
import com.revature.models.User;
import com.revature.repositories.BackorderDAO;
import com.revature.repositories.CartDAO;
import com.revature.repositories.CouponDAO;
import com.revature.repositories.ItemDAO;
import com.revature.repositories.TransactionDAO;
import com.revature.repositories.TuiDAO;
import com.revature.repositories.UserDAO;

@Service
public class AdminService /*extends EmployeeService*/ {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TransactionDAO tDAO;
	@Autowired
	private ItemDAO iDAO;
	@Autowired
	private TuiDAO tuiDAO;
	@Autowired
	private CartDAO cDAO;
	@Autowired
	private BackorderDAO boDAO;
	@Autowired
	private CouponDAO coupDAO;

	public User addUser(Key k, User u) {
		MDC.put("Action", "Adm Add User");
		if (userDAO.findByUname(u.getUname()).isPresent()) {
			throw new InvalidException(String.format("INSERT: Username %s already exists.", u.getUname()));
		}
		if (u.getUid() != null || u.getSid() != null) {
			throw new InvalidException (String.format("INSERT: Invalid ID(s) (%d:%d) passed during insertion.", u.getUid(), u.getSid()));
		}
		return userDAO.save(u);
	}

	public User hireCustomer(Key k, User u) {
		MDC.put("Action", "Adm Hire Customer");
		if (!userDAO.findById(u.getUid())
			.orElseThrow(()->new InvalidException(String.format("UPDATE: Customer %d does not exist.", u.getUid())))
		.getAccesslevel().equals("Customer")) {
			throw new InvalidException(String.format("UPDATE: User %d is not a customer to hire.", u.getUid()));
		}
		if ((!u.getAccesslevel().equals("Employee")&&!u.getAccesslevel().equals("Admin"))||u.getAccesslevel().equals("Customer")) {
			throw new InvalidException ("UPDATE: User accesslevel has not been changed.");
		}
		return userDAO.save(u);
	}
	
	public User releaseEmployee(Key k, User u) {
		MDC.put("Action", "Adm Release Employee");
		if (!userDAO.findById(u.getUid())
				.orElseThrow(()->new InvalidException(String.format("UPDATE: Employee %d does not exist.", u.getUid())))
			.getAccesslevel().equals("Customer")) {
				throw new InvalidException(String.format("UPDATE: User %d is not an employee to release.", u.getUid()));
			}
		if (!u.getAccesslevel().equals("Customer")) {
			throw new InvalidException ("UPDATE: User accesslevel is invalid.");
		}
		return userDAO.save(u);
	}
	
	public List<User> displayAllUsers(Key k) {
		MDC.put("Action", "Adm Display Users");
		return userDAO.findAll();
	}	

	public boolean delCustomer(Key k, User u) {
		MDC.put("Action", "Adm Delete Customer");
		if (!userDAO.existsById(u.getUid())) {
			throw new InvalidException(String.format("DELETE: User %d does not exist.", u.getUid()));
		}
		//if require by table constraints
		List<Transaction> ts = tDAO.findAllByUid(u.getUid());
		for(Transaction t : ts) {
			tuiDAO.deleteByTransaction(t);
		}
		tDAO.deleteByUid(u.getUid());
		boDAO.deleteByUid(u.getUid());
		cDAO.deleteByUid(u.getUid());
		userDAO.deleteById(u.getUid());
		return true;
	}
	
	public User updateUserAtRequestByUser(Key k, User u) {
		MDC.put("Action", "Adm Modify User");
		if (!userDAO.existsById(u.getUid())||!userDAO.findByUname(u.getUname()).isPresent()) {
			throw new InvalidException(String.format("UPDATE: Invalid ID %d or username %s passed during User modification.", u.getUid(), u.getUname()));
		}
		if (u.getUid() == k.getUid()) {
			throw new InvalidException(String.format("UPDATE: User %d attempting to update self incorrectly.",u.getUid()));
		}
		return userDAO.save(u);
	}
	
	public boolean delUserTransaction(Key k, Transaction t) {
		MDC.put("Action", "Adm Delete Transaction");
		if(k.getUid()==tDAO.findById(t.getTid()).orElseThrow(() -> new InvalidException(String.format("DELETE: Transaction %d does not exist.", t.getTid()))).getUid()) {
			throw new InvalidException(String.format("DELETE: User %d attempted to delete their own transaction.", k.getUid()));
		}
		tuiDAO.deleteByTransaction(t);
		tDAO.deleteById(t.getTid());
		return true;
	}
	
	public List<Transaction> displayUserTransactions(Key k, User u) {
		MDC.put("Action", "Adm Display Transactions");
		if (!userDAO.existsById(u.getUid())) {
			throw new InvalidException(String.format("SELECT: User %d does not exist.",u.getUid()));
		}
		return tDAO.findAllByUid(u.getUid());
	}
	
	public List<DEPRECIATEDCartItem> displayUserTransactionItems(Key k, Transaction t){
		MDC.put("Action", "Adm Display Transaction Items");
		if (!tDAO.existsById(t.getTid())) {
			throw new InvalidException(String.format("SELECT: Transaction %d does not exist.",t.getTid()));
		}
		List<TUI> cips = tuiDAO.findAllByTransaction(t);
		List<DEPRECIATEDCartItem> cis = new ArrayList<>();
		for (TUI tp : cips) {
			DEPRECIATEDCartItem ci = buildTui(tp);
			if (ci.toString(false) != null || ci.getI().toString(false) != null) {
				cis.add(ci);
			}
		}
		return cis;
	}
	
	public DEPRECIATEDCartItem modUserTransactionItem(Key k, TUI tp) {
		MDC.put("Action", "Adm Modify Transaction Item");
//		if (!tDAO.existsById(tp.getTid())||(tp.getCid()!=0 && !coupDAO.existsById(tp.getCid()))) {
//			throw new InvalidException(String.format("UPDATE: Transaction %d or Coupon %d does not exist.", tp.getTid(), tp.getCid()));
//		}
//		if(tp.getQuantity() < 1) {
//			throw new InvalidException("UPDATE: Transaction item quantity must be greater than 0.");
//		}
//		//transaction already confirmed above
//		Transaction t = tDAO.findById(tp.getTid()).get();
//		List<DEPRECIATEDCartItem> cis = displayUserTransactionItems(k, t);
//		DEPRECIATEDCartItem ci=null;
//		for (DEPRECIATEDCartItem i: cis) {
//			if ( i.getI().getIid()==tp.getIid()&&i.getUtid()==tp.getTid()) {
//				ci = i;
//			}
//		}
//		if(ci == null) {throw new InvalidException("SELECT: Unable to find matching item in user's transaction item list.");}
//		
//		if(ci.getI().getQuantity() < tp.getQuantity()) {
//				boDAO.save(new Backorder(t.getUid(), tp.getIid(), tp.getQuantity(), tp.getCid()));
//				log.info("Item {}:{} was put on backorder due to limited stock on hand.", ci.getI().getIid(),
//						ci.getI().getUnitname());
//			} else {
//				if (setNewQuantities(ci.getI(), tp.getQuantity()-ci.getCartQuantity()/*newQuantity - oldQuantity*/)) {
//					tuiDAO.deleteByTidAndIid(tp.getTid(),tp.getIid());
//					//tuiDAO.delete(new TuiProto(ci.getUtid(),ci.getCartQuantity(),ci.getCid(),ci.getI().getIid()));
//					tuiDAO.save(tp);
//				} else {
//					boDAO.save(new Backorder(t.getUid(), tp.getIid(), tp.getQuantity(), tp.getCid()));
//					log.error("Item {}'s quantities could not be updated, item put on user {}'s backorder.",
//							tp.getIid(), t.getUid());
//				}
//			}
//
//			cis = displayUserTransactionItems(k, t);
//			t.setTotalcost(calculateTotal(cis));
//			t = tDAO.save(t);
//		// call third party function to reimburse/bill user for changes
//		return buildTui(tp);
		return new DEPRECIATEDCartItem();
	}
	
	public boolean delUserTransactionItem(Key k, TUI tp) {
		MDC.put("Action", "Adm Delete Transaction Item");
		if(!tDAO.existsById(tp.getTransaction().getTid())) {
			throw new InvalidException(String.format("DELETE: Transaction %d containing given item does not exist.",tp.getTransaction().getTid()));
		}
		tuiDAO.delete(tp);
		return true;
	}
	
	public List<Coupon> displayCoupons(Key k) {
		MDC.put("Action", "Adm Display Coupons");
		return coupDAO.findAll();
	}
	
	public Coupon addCoupon(Key k, Coupon c) {
		MDC.put("Action", "Adm Add Coupon");
		if(c.getCid() != 0) {
			throw new InvalidException(String.format("INSERT: Invalid coupon id %d.", c.getCid()));
		}
		return coupDAO.save(c);
	}
	
	public Coupon modCoupon(Key k, Coupon c) {
		MDC.put("Action", "Adm Modify Coupon");
		if(!coupDAO.existsById(c.getCid())) {
			throw new InvalidException(String.format("UPDATE: Coupon %d does not exist.", c.getCid()));
		}
		return coupDAO.save(c);
	}
	
	public boolean delCoupon(Key k, Coupon c) {
		MDC.put("Action", "Adm Delete Coupon");
		if(!coupDAO.existsById(c.getCid())) {
			throw new InvalidException(String.format("UPDATE: Coupon %d does not exist.", c.getCid()));
		}
		if(!cDAO.findAllByCid(c.getCid()).isEmpty()
				|| !boDAO.findAllByCid(c.getCid()).isEmpty()
				|| !tuiDAO.findAllByCoupon(c).isEmpty()) {
			throw new InvalidException(String.format("DELETE: Coupon %d is present in a cart/backorder/transaction.", c.getCid()));
		}
		coupDAO.delete(c);
		return true;
	}

	private DEPRECIATEDCartItem buildTui(TUI tp) {//converts to CartItem and adds item as object
		DEPRECIATEDCartItem ci = new DEPRECIATEDCartItem();
		ci.setCartQuantity(tp.getCid());
		ci.setUtid(tp.getTransaction().getTid());
		ci.setCartQuantity(tp.getQuantity());
		Optional<Item> i = iDAO.findById(tp.getItem().getIid());
		if (i.isPresent()) {
			ci.setI(i.get());
		} else {
//			log.error("SELECT: Item {} does not exist.", cip.getIid());
//			return new CartItem();
			throw new InvalidException(String.format("SELECT: Item %d does not exist.",tp.getItem().getIid()));
		}
		return ci;
	}

	private double calculateTotal(List<DEPRECIATEDCartItem> cis) {
		double total = 0.0;
		double coupon = 00.00;
		double tax = 00.00;
		for (DEPRECIATEDCartItem cs : cis) {
			total += cs.getI().getSellingprice() * cs.getCartQuantity() * (coupon / 100 + 1) * (tax / 100 + 1);
		}
		return total;
	}

	// MUST NOT THROW ERROR, WILL DISRUPT CHECKOUT PROCESS
	// ADMIN MUST HANDLE LOGGED ISSUE VIA BACKORDER TABLE
	private boolean setNewQuantities(Item i, long amountSold) {
		if (iDAO.existsById(i.getIid())) {
			i.setQuantity(i.getQuantity() - amountSold);
			i.setTotalpurchases(i.getTotalpurchases() + amountSold);
			iDAO.save(i);
			return true;
		} else {
			log.error("Unable to update item {}'s quantity", i.getIid());
			return false;
		}
	}
}
