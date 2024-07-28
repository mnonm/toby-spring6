package tobyspring.spring6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebApiExRateProvider implements ExRateProvider {
	@Override
	public BigDecimal getExRate(String currency) throws IOException {
		URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = br.lines().collect(Collectors.joining());
		br.close();

		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(response, ExRateData.class);
		BigDecimal exRate = data.rates().get("KRW");
		return exRate;
	}
}
