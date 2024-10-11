package tobyspring.spring6;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import tobyspring.spring6.data.JdbcOrderRepository;
import tobyspring.spring6.order.OrderRepository;
import tobyspring.spring6.order.OrderService;
import tobyspring.spring6.order.OrderServiceImpl;
import tobyspring.spring6.order.OrderServiceTxProxy;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

	@Bean
	public OrderRepository orderRepository(DataSource dataSource) {
		return new JdbcOrderRepository(dataSource);
	}

	@Bean
	public OrderService orderService(PlatformTransactionManager transactionManager, OrderRepository orderRepository) {
		return new OrderServiceTxProxy(
			new OrderServiceImpl(orderRepository),
			transactionManager
		);
	}
}
