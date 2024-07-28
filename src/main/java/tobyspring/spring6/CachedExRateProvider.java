package tobyspring.spring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {
	private final ExRateProvider target;

	private BigDecimal cachedExRate;
	private LocalDateTime cacheExpiryTime;

	public CachedExRateProvider(ExRateProvider target) {
		this.target = target;
	}

	@Override
	public BigDecimal getExRate(String currency) throws IOException {
		if (cachedExRate == null || this.cacheExpiryTime.isBefore(LocalDateTime.now())) {
			this.cachedExRate = target.getExRate(currency);
			this.cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
		}

		return this.cachedExRate;
	}
}
