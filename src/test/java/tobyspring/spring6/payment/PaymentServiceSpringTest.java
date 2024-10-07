package tobyspring.spring6.payment;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tobyspring.spring6.TestPaymentConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
public class PaymentServiceSpringTest {

	@Autowired
	PaymentService paymentService;

	@Autowired
	Clock clock;

	/**
	 * interface를 사용하는게 유연하지만 test에서는 ExRateProvider를 제어하기 위해 구현체를 사용한다
	 */
	@Autowired
	ExRateProviderStub exRateProviderStub;

	@Test
	void convertedAmount() throws IOException {
		// exRate: 1000
		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		// BigDecimal 비교시 사용
		assertThat(payment.getExRate()).isEqualByComparingTo(String.valueOf(1_000));
		assertThat(payment.getConvertedAmount()).isEqualByComparingTo(String.valueOf(10_000));

		// exRate: 500
		exRateProviderStub.setExRate(valueOf(500));
		Payment payment2 = paymentService.prepare(1L, "USD", TEN);

		// BigDecimal 비교시 사용
		assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
		assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
	}

	@Test
	void validUntil() throws IOException {
		Payment payment = paymentService.prepare(1L, "USD", TEN);

		LocalDateTime now = LocalDateTime.now(this.clock);
		LocalDateTime expectedValidUntil = now.plusMinutes(30);

		Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
	}
}
