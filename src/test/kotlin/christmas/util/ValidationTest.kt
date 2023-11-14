package christmas.util

import christmas.util.Validation.validateVisitDate


import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.junit.jupiter.api.assertThrows

class ValidationTest {

    @ParameterizedTest
    @ValueSource(strings = [" ", "abc", "three"])
    fun `식당 방문 날짜 입력이 숫자가 아니거나 빈 칸인 경우 예외 발생`(date: String) {
        assertThrows<IllegalArgumentException> {
            validateVisitDate(date)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "35", "167"])
    fun `식당 방문 날짜 입력이 1~31이 아닌 숫자일 경우`(date: String) {
        assertThrows<IllegalArgumentException> {
            validateVisitDate(date)
        }
    }


}