package unishop.services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import unishop.entities.Cashier;
import unishop.entities.Product;
import unishop.entities.ProductDetails;
import unishop.entities.Shop;
import unishop.exceptions.ValidationException;

public class ShopService {

	public void restockShop(Shop shop, Product product, int amount, float priceIncrPercentage,
			float priceDecrPercentage) {
		ProductDetails pDetails = null;
		for (ProductDetails pd : shop.getProducts()) {
			if (pd.getProduct().getName().equals(product.getName())) {
				pDetails = pd;
				pDetails.setAmount(pDetails.getAmount() + amount);
				pDetails.setPriceIncrPercentage(priceIncrPercentage);
				pDetails.setPriceDecrPercentage(priceDecrPercentage);
				break;
			}
		}
		if (pDetails == null) {
			pDetails = new ProductDetails(product, amount, priceIncrPercentage, priceDecrPercentage);
			shop.getProducts().add(pDetails);
		}

	}

	public void sellStock(Shop shop, Map<Product, Integer> products, Cashier cashier) {
		List<String> lines = new ArrayList<String>();
		for (Product product : products.keySet()) {
			ProductDetails pDetails = null;
			StringBuilder receptLine = new StringBuilder();
			for (ProductDetails pds : shop.getProducts()) {
				if (pds.getProduct().getName().equals(product.getName())) {
					pDetails = pds;
					Integer amount = products.get(product);
					validateProductSale(pDetails, amount);
					pDetails.setAmount(pDetails.getAmount() - amount);
					receptLine.append("Product: ").append(product.getName()).append(" ").append(amount).append("x")
							.append(" Price: $").append(product.getPrice() * amount).append("\n");

					lines.add(receptLine.toString());
					break;
				}
			}
			if (pDetails == null) {
				throw new ValidationException("Product not found");
			}
		}
		issueReceipt(new Random().nextInt(1000), shop, cashier, lines);

	}

	private void validateProductSale(ProductDetails pDetails, int amount) {
		if (pDetails.getAmount() < amount) {
			throw new ValidationException("Insufficient amount");
		}
		if (pDetails.getProduct().getDateOfExp().isBefore(LocalDate.now())) {
			throw new ValidationException("Product has expired");
		}
	}

	private void issueReceipt(int number, Shop shop, Cashier cashier, List<String> lines) {
		StringBuilder receipt = new StringBuilder();
		receipt.append("Receipt number: ").append(number).append("\n");
		receipt.append("Shop: ").append(shop.getName()).append("\n");
		receipt.append("Cashier: ").append(cashier.getName()).append("\n\n");
		for (String line : lines) {
			receipt.append(line);

		}

		try (FileWriter receipts = new FileWriter("receipt.txt")) {

			receipts.write(receipt.toString());
			receipts.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
