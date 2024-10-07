package tobyspring.spring6.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import tobyspring.spring6.payment.ExRateProvider;

public class CachedExRateProvider implements ExRateProvider {
	private final ExRateProvider target;

	private BigDecimal cachedExRate;
	private LocalDateTime cacheExpiryTime;

	public CachedExRateProvider(ExRateProvider target) {
		this.target = target;
	}

	@Override
	public BigDecimal getExRate(String currency) {
		if (cachedExRate == null || this.cacheExpiryTime.isBefore(LocalDateTime.now())) {
			this.cachedExRate = target.getExRate(currency);
			this.cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
		}

		return this.cachedExRate;
	}
}
