package tobyspring.spring6.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.spring6.order.Order;
import tobyspring.spring6.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Order order) {
		em.persist(order);
	}
}
