package christmas.util

import christmas.util.Validation.validateOrderMenu
import christmas.util.Validation.validateVisitDate


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
    fun `식당 방문 날짜 입력이 1~31이 아닌 숫자일 경우 예외 발생`(date: String) {
        assertThrows<IllegalArgumentException> {
            validateVisitDate(date)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스1,제로콜라3", "티본스테이크-0,바비큐립-0", "바닐라케이크-2,제로콜라-1"])
    fun `주문 메뉴와 개수에 대한 입력 형식이 올바르지 않을 경우 예외 발생`(order: String) {
        assertThrows<IllegalArgumentException> {
            validateOrderMenu(order)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,타파스-1", "양송이수프-3,양송이수프-6"])
    fun `주문 메뉴와 개수에 대한 입력 중에 동일 메뉴가 있을 경우 예외 발생`(order: String) {
        assertThrows<IllegalArgumentException> {
            validateOrderMenu(order)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["제로콜라-6,레드와인-1", "샴페인-2"])
    fun `주문 메뉴와 개수에 대한 입력 중에 음료만 있을 경우 예외 발생`(order: String) {
        assertThrows<IllegalArgumentException> {
            validateOrderMenu(order)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["티본스테이크-5,바비큐립-6,초코케이크-10", "양송이수프-9,시저샐러드-12"])
    fun `주문 메뉴의 개수가 20개가 넘을 경우 예외 발생`(order: String) {
        assertThrows<IllegalArgumentException> {
            validateOrderMenu(order)
        }
    }


}