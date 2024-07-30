package tobyspring.spring6;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tobyspring.spring6.payment.Payment;
import tobyspring.spring6.payment.PaymentService;

public class Client {
	public static void main(String[] args) throws IOException {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
		PaymentService paymentService = beanFactory.getBean(PaymentService.class);

		Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(payment);
	}
}
