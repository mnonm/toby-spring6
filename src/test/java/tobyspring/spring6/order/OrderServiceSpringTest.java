package tobyspring.spring6.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tobyspring.spring6.OrderConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
	@Autowired
	OrderService orderService;

	@Test
	void createOrder() {
		var order = orderService.createOrder("0100", BigDecimal.ONE);

		assertThat(order.getId()).isGreaterThan(0);
	}
}
