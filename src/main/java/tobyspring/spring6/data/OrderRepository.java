package tobyspring.spring6.data;

import java.math.BigDecimal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import tobyspring.spring6.order.Order;

public class OrderRepository {

	private final EntityManagerFactory emf;

	public OrderRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void save(Order order) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		try {
			em.persist(order);
			em.flush();

			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive())
				transaction.rollback();
			throw e;
		} finally {
			if (em.isOpen())
				em.close();
		}
	}
}
