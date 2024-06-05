package unishop.entities;

public class ProductDetails {

	private Product product;
	private int amount;
	private float priceIncrPercentage;
	private float priceDecrPercentage;

	public ProductDetails(Product product, int amount, float priceIncrPercentage, float priceDecrPercentage) {
		this.product = product;
		this.amount = amount;
		this.priceIncrPercentage = priceIncrPercentage;
		this.priceDecrPercentage = priceDecrPercentage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPriceIncrPercentage() {
		return priceIncrPercentage;
	}

	public void setPriceIncrPercentage(float priceIncrPercentage) {
		this.priceIncrPercentage = priceIncrPercentage;
	}

	public float getPriceDecrPercentage() {
		return priceDecrPercentage;
	}

	public void setPriceDecrPercentage(float priceDecrPercentage) {
		this.priceDecrPercentage = priceDecrPercentage;
	}

}
