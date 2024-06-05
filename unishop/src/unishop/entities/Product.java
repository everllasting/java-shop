package unishop.entities;

import java.time.LocalDate;

public class Product {

	private int id;
	private String name;
	private StockCategory category;
	private double price;
	private LocalDate dateOfExp;

	public Product(int id, String name, StockCategory category, double price, LocalDate dateOfExp) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.dateOfExp = dateOfExp;
	}

	public void priceFlux() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StockCategory getCategory() {
		return category;
	}

	public void setCategory(StockCategory category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getDateOfExp() {
		return dateOfExp;
	}

	public void setDateOfExp(LocalDate dateOfExp) {
		this.dateOfExp = dateOfExp;
	}

}
