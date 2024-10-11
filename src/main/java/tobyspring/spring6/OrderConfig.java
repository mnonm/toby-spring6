package tobyspring.spring6;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tobyspring.spring6.data.JdbcOrderRepository;
import tobyspring.spring6.order.OrderRepository;
import tobyspring.spring6.order.OrderService;
import tobyspring.spring6.order.OrderServiceImpl;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {

	@Bean
	public OrderRepository orderRepository(DataSource dataSource) {
		return new JdbcOrderRepository(dataSource);
	}

	@Bean
	public OrderService orderService(OrderRepository orderRepository) {
		return new OrderServiceImpl(orderRepository);
	}
}
