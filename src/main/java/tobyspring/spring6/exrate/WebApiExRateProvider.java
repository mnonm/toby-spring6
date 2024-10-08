package tobyspring.spring6.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tobyspring.spring6.api.SimpleApiExecutor;
import tobyspring.spring6.payment.ExRateProvider;

@Component
public class WebApiExRateProvider implements ExRateProvider {
	@Override
	public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return runApiForExRate(url);
	}

	private static BigDecimal runApiForExRate(String url) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			response = new SimpleApiExecutor().execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return extractExRate(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static BigDecimal extractExRate(String response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(response, ExRateData.class);
		return data.rates().get("KRW");
	}
}
