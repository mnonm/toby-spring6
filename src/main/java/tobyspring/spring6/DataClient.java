package tobyspring.spring6;

import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tobyspring.spring6.data.OrderRepository;
import tobyspring.spring6.order.Order;

public class DataClient {
	public static void main(String[] args) {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
		OrderRepository repository = beanFactory.getBean(OrderRepository.class);

		Order order = new Order("100", BigDecimal.TEN);
		repository.save(order);

		System.out.println("order = " + order);

		Order order2 = new Order("100", BigDecimal.ONE);
		repository.save(order2);
	}
}
