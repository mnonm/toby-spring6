package tobyspring.spring6.payment;

import java.math.BigDecimal;

public interface ExRateProvider {
	BigDecimal getExRate(String currency);
}
