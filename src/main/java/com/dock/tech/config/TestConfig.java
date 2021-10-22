package com.dock.tech.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dock.tech.entities.Category;
import com.dock.tech.entities.Order;
import com.dock.tech.entities.OrderItem;
import com.dock.tech.entities.Payment;
import com.dock.tech.entities.Product;
import com.dock.tech.entities.User;
import com.dock.tech.entities.enums.OrderStatusEnum;
import com.dock.tech.repositories.CategoryRepository;
import com.dock.tech.repositories.OrderItemRepository;
import com.dock.tech.repositories.OrderRepository;
import com.dock.tech.repositories.ProductRepository;
import com.dock.tech.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Scent");
		Category cat3 = new Category(null, "Computers");

		Product p1 = new Product(null, "Pendrive", "50 gb.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Full HD.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "MacOS 13.", 4250.0, "");
		Product p4 = new Product(null, "Alienware", "Core I5 12gb RAM SSD gb.", 6200.0, "");
		Product p5 = new Product(null, "Perfum", "Hugo Boss.", 100.99, "");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		User hugo = new User(null, "McGregor", "hugomartinez@gmail.com", "988888888", "123456");
		User guilhermo = new User(null, "Guilhermo", "guilhermoh@gmail.com", "977777777", "123456");
		User andrews = new User(null, "Andrews", "andrews@gmail.com", "976666677", "123456");
		User belli = new User(null, "Belli", "rodolfo@gmail.com", "97998877", "123456");
		User maringa = new User(null, "Maringa", "joaopaulo@gmail.com", "979999877", "123456");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatusEnum.PAID, hugo);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatusEnum.WAITING_PAYMENT, guilhermo);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatusEnum.WAITING_PAYMENT, maringa);

		userRepository.saveAll(Arrays.asList(hugo, guilhermo, andrews, belli, maringa));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		OrderItem orderItem1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem orderItem2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem orderItem3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem orderItem4 = new OrderItem(o3, p5, 2, p5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));

		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);

		orderRepository.save(o1);
	}
}
