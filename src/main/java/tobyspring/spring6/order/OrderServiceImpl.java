package tobyspring.spring6.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order createOrder(String no, BigDecimal total) {
		Order order = new Order(no, total);

		orderRepository.save(order);
		return order;
	}

	@Override
	public List<Order> createOrders(List<OrderReq> reqs) {
		return reqs.stream()
			.map(req -> createOrder(req.no(), req.total()))
			.toList();
	}
}
