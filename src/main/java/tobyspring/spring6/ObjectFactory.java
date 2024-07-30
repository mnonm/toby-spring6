package tobyspring.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tobyspring.spring6.exrate.CachedExRateProvider;
import tobyspring.spring6.payment.ExRateProvider;
import tobyspring.spring6.exrate.WebApiExRateProvider;
import tobyspring.spring6.payment.PaymentService;

@Configuration
public class ObjectFactory {
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cachedExRateProvider());
	}

	@Bean
	public ExRateProvider cachedExRateProvider() {
		return new CachedExRateProvider(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new WebApiExRateProvider();
	}
}
