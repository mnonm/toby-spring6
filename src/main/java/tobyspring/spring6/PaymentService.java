package tobyspring.spring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {
	private final ExRateProvider exRateProvider;

	public PaymentService(ExRateProvider exRateProvider) {
		this.exRateProvider = exRateProvider;
	}

	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
		// 환율 가져오기
		BigDecimal exRate = exRateProvider.getExRate(currency);
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

		return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
	}
}
