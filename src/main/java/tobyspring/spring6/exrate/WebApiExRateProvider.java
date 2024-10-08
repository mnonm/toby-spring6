package tobyspring.spring6.exrate;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import tobyspring.spring6.api.ApiTemplate;
import tobyspring.spring6.api.ErApiExRateExtractor;
import tobyspring.spring6.api.HttpClientApiExecutor;
import tobyspring.spring6.payment.ExRateProvider;

@Component
public class WebApiExRateProvider implements ExRateProvider {

	ApiTemplate apiTemplate = new ApiTemplate();

	@Override
	public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
	}
}
