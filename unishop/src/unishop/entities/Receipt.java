package unishop.entities;

import java.time.LocalDateTime;
import java.util.Map;

public class Receipt {

	private int number;
	private Cashier cashier;
	private LocalDateTime date;
	private Map<ProductDetails, Integer> products;
	private double totalPrice;

	public Receipt(int number, Cashier cashier, LocalDateTime date, Map<ProductDetails, Integer> products) {
		this.number = number;
		this.cashier = cashier;
		this.date = date;
		this.products = products;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Map<ProductDetails, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<ProductDetails, Integer> products) {
		this.products = products;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
