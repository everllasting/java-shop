package unishop.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unishop.entities.Cashier;
import unishop.entities.Product;
import unishop.entities.Shop;
import unishop.entities.StockCategory;
import unishop.exceptions.ValidationException;

public class ShopServiceTest {

	private Shop shop1;
	private Cashier cashier1;
	private Product beer;
	private Product bread;
	private Product whey;

	ShopService service = new ShopService();

	@BeforeEach
	void before() {
		shop1 = new Shop();
		shop1.setName("Lidl");
		cashier1 = new Cashier();
		cashier1.setName("Alex");
		beer = new Product(1, "Beer", StockCategory.BEVERAGES, 2.20, LocalDate.of(2025, 10, 22));
		bread = new Product(1, "Bread", StockCategory.FOOD, 1.60, LocalDate.of(2024, 6, 12));
		whey = new Product(1, "Whey", StockCategory.FOOD, 0.5, LocalDate.of(2025, 6, 12));
	}

	@Test
	void testRestockShop() {
		service.restockShop(shop1, beer, 10, 10, 15);
		service.restockShop(shop1, bread, 15, 10, 10);
		assertEquals(2, shop1.getProducts().size());
		assertEquals("Beer", shop1.getProducts().get(0).getProduct().getName());
		assertEquals(10, shop1.getProducts().get(0).getAmount());
		assertEquals(2, shop1.getProducts().size());
		assertEquals("Bread", shop1.getProducts().get(1).getProduct().getName());
		assertEquals(15, shop1.getProducts().get(1).getAmount());
	}

	@Test
	void testRestockShopRightProduct() {
		service.restockShop(shop1, beer, 10, 10, 15);
		service.restockShop(shop1, bread, 15, 10, 10);
		assertEquals(2, shop1.getProducts().size());
		assertNotEquals("Bread", shop1.getProducts().get(0).getProduct().getName());
		assertEquals(2, shop1.getProducts().size());
		assertNotEquals("Beer", shop1.getProducts().get(1).getProduct().getName());
	}

	@Test
	void testRestockShopRepeat() {
		service.restockShop(shop1, beer, 10, 10, 15);
		assertEquals(1, shop1.getProducts().size());
		assertEquals("Beer", shop1.getProducts().get(0).getProduct().getName());
		assertEquals(10, shop1.getProducts().get(0).getAmount());

		service.restockShop(shop1, beer, 5, 11, 16);
		assertEquals(1, shop1.getProducts().size());
		assertEquals("Beer", shop1.getProducts().get(0).getProduct().getName());
		assertEquals(15, shop1.getProducts().get(0).getAmount());
		assertEquals(11, shop1.getProducts().get(0).getPriceIncrPercentage());
		assertEquals(16, shop1.getProducts().get(0).getPriceDecrPercentage());

	}

	@Test
	void testSaleExpired() {
		bread.setDateOfExp(LocalDate.of(2023, 11, 22));
		service.restockShop(shop1, bread, 10, 10, 15);
		Map<Product, Integer> map = new HashMap<Product, Integer>();
		map.put(bread, 1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			service.sellStock(shop1, map, cashier1);
		});

		assertEquals("Product has expired", exception.getMessage());
	}

	@Test
	void testInsufficentAmount() {
		service.restockShop(shop1, bread, 10, 10, 15);

		Map<Product, Integer> map = new HashMap<Product, Integer>();
		map.put(bread, 11);

		Exception exception2 = assertThrows(ValidationException.class, () -> {
			service.sellStock(shop1, map, cashier1);
		});

		assertEquals("Insufficient amount", exception2.getMessage());

	}

	@Test
	void testProductNotFound() {
		service.restockShop(shop1, bread, 1, 10, 15);
		Map<Product, Integer> map = new HashMap<Product, Integer>();
		map.put(whey, 11);

		assertThrows(ValidationException.class, () -> {

			service.sellStock(shop1, map, cashier1);
		});
	}

	@Test
	void testSelltock() {
		service.restockShop(shop1, bread, 3, 10, 15);
		service.restockShop(shop1, whey, 3, 10, 15);
		Map<Product, Integer> map = new HashMap<Product, Integer>();
		map.put(bread, 2);
		map.put(whey, 2);
		service.sellStock(shop1, map, cashier1);
		assertNotEquals(2, shop1.getProducts().get(0).getAmount());
		assertEquals(1, shop1.getProducts().get(0).getAmount());
		assertNotEquals(2, shop1.getProducts().get(1).getAmount());
		assertEquals(1, shop1.getProducts().get(1).getAmount());

	}

	// Insufficient amount
	// No product
	// Successful sale

}
