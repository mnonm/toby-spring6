package tobyspring.spring6;

import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import tobyspring.spring6.order.Order;

public class DataClient {
	public static void main(String[] args) {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
		EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Order order = new Order("100", BigDecimal.TEN);
		em.persist(order);

		System.out.println("order = " + order);

		em.getTransaction().commit();
		em.close();
	}
}
