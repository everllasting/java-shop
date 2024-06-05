package unishop.entities;

import java.util.ArrayList;
import java.util.List;

public class Shop {

	private String name;

	private List<ProductDetails> products = new ArrayList<>();

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}

	public List<Cashier> getCashier() {
		return cashier;
	}

	public void setCashier(List<Cashier> cashier) {
		this.cashier = cashier;
	}

	public List<Receipt> getReceipt() {
		return receipt;
	}

	public void setReceipt(List<Receipt> receipt) {
		this.receipt = receipt;
	}

	private List<Cashier> cashier;
	private List<Receipt> receipt;

	public Shop() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
