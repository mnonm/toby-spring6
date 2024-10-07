package tobyspring.spring6.payment;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentServiceTest {

	@Test
	@DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
	void convertedAmount() throws IOException {
		testAmount(valueOf(500), valueOf(5_000));
		testAmount(valueOf(1_000), valueOf(10_000));
		testAmount(valueOf(3_000), valueOf(30_000));

		// 원화환산금액의 유효시간 계산
		// assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
		// assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
	}

	private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		// BigDecimal 비교시 사용
		assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
		assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
	}
}
