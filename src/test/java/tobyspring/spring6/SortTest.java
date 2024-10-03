package tobyspring.spring6;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortTest {

	Sort sort;

	@BeforeEach
	void beforeEach() {
		sort = new Sort();
	}

	@Test
	void sort() {

		// 실행
		List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

		// 검증
		assertThat(list).isEqualTo(List.of("b", "aa"));
	}

	@Test
	void sort3Items() {
		// 실행
		List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

		// 검증
		assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
	}

	@Test
	void sortAlreadySorted() {
		// 준비
		Sort sort = new Sort();

		// 실행
		List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

		// 검증
		assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
	}
}
