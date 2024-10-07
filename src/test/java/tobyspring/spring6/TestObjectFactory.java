package tobyspring.spring6;

import static java.math.BigDecimal.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tobyspring.spring6.payment.ExRateProvider;
import tobyspring.spring6.payment.ExRateProviderStub;
import tobyspring.spring6.payment.PaymentService;

@Configuration
public class TestObjectFactory {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new ExRateProviderStub(valueOf(1_000));
	}
}
