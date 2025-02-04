package tobyspring.spring6;

import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;

import tobyspring.spring6.order.Order;
import tobyspring.spring6.order.OrderService;

public class OrderClient {
	public static void main(String[] args) {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
		OrderService service = beanFactory.getBean(OrderService.class);
		JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

		Order order = service.createOrder("0100", BigDecimal.TEN);
		System.out.println("order = " + order);
	}
}
