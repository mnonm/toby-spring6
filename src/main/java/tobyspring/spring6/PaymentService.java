package tobyspring.spring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

abstract public class PaymentService {
	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws
		IOException {
		// 환율 가져오기
		BigDecimal exRate = getExRate(currency);
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

		return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
	}

	abstract BigDecimal getExRate(String currency) throws IOException;

	public static void main(String[] args) throws IOException {
		PaymentService paymentService = new WebApiExRatePaymentService();
		Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(payment);
	}
}
