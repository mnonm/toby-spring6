package tobyspring.spring6.exrate;

import java.math.BigDecimal;

import tobyspring.spring6.payment.ExRateProvider;

public class SimpleExRateProvider implements ExRateProvider {
	@Override
	public BigDecimal getExRate(String currency) {
		if (currency.equals("USD")) {
			return BigDecimal.valueOf(1000);
		}
		throw new IllegalStateException("지원되지 않는 통화입니다.");
	}
}
