package tobyspring.spring6.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import tobyspring.spring6.api.ErApiExRateExtractor;
import tobyspring.spring6.api.ExRateExtractor;
import tobyspring.spring6.api.SimpleApiExecutor;
import tobyspring.spring6.payment.ExRateProvider;

@Component
public class WebApiExRateProvider implements ExRateProvider {
	@Override
	public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
	}

	private static BigDecimal runApiForExRate(String url, SimpleApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			response = apiExecutor.execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return exRateExtractor.extract(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
