package tobyspring.spring6.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.spring6.order.Order;

public class OrderRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}
}
