package tobyspring.spring6.exrate;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import tobyspring.spring6.api.ApiTemplate;
import tobyspring.spring6.payment.ExRateProvider;

@Component
public class WebApiExRateProvider implements ExRateProvider {

	private final ApiTemplate apiTemplate;

	public WebApiExRateProvider(ApiTemplate apiTemplate) {
		this.apiTemplate = apiTemplate;
	}

	@Override
	public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return apiTemplate.getExRate(url);
	}
}
