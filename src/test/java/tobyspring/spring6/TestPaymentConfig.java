package tobyspring.spring6;

import static java.math.BigDecimal.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tobyspring.spring6.payment.ExRateProvider;
import tobyspring.spring6.payment.ExRateProviderStub;
import tobyspring.spring6.payment.PaymentService;

@Configuration
public class TestPaymentConfig {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider(), clock());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new ExRateProviderStub(valueOf(1_000));
	}

	@Bean
	public Clock clock() {
		return Clock.fixed(Instant.now(), ZoneId.systemDefault());
	}
}
