package tobyspring.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

import tobyspring.spring6.data.OrderRepository;
import tobyspring.spring6.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

	@Bean
	public OrderRepository orderRepository() {
		return new OrderRepository();
	}

	@Bean
	public OrderService orderService(JpaTransactionManager transactionManager) {
		return new OrderService(orderRepository(), transactionManager);
	}
}
