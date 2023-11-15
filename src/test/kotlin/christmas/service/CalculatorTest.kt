package christmas.service

import christmas.model.Menu
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CalculatorTest {

    private lateinit var calculator: Calculator
    private val event = Event()

    @BeforeEach
    fun setUp() {
        calculator = Calculator(event)
    }

    @ParameterizedTest
    @MethodSource("orderMenu")
    fun `할인 전 총 주문 금액에 대한 테스트`(order: Map<Menu, Int>, expectedAmount: Int) {
        val calculatorAmount = calculator.calculateBeforeDiscount(order)

        assertEquals(expectedAmount, calculatorAmount)
    }

    @ParameterizedTest
    @MethodSource("orderBenefits")
    fun `혜택 금액에 대한 테스트`(
        date: Int,
        order: Map<Menu, Int>,
        freeGift: Boolean,
        expectedBenefits: Int
    ) {
        val benefits = calculator.calculateBenefitPrice(date, order, freeGift)

        assertEquals(expectedBenefits, benefits)
    }

    @Test
    fun calculateTotalPrice() {
    }


    companion object {
        @JvmStatic
        fun orderMenu(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    mapOf(
                        Menu("양송이수프", 6000) to 2,
                        Menu("티본스테이크", 55000) to 3,
                        Menu("초코케이크", 15000) to 2,
                        Menu("제로콜라", 3000) to 3
                    ), 216000
                ),
                Arguments.of(
                    mapOf(
                        Menu("타파스", 5500) to 2,
                        Menu("바비큐립", 54000) to 3,
                        Menu("아이스크림", 5000) to 2,
                        Menu("레드와인", 60000) to 3
                    ), 363000
                )
            )
        }

        @JvmStatic
        fun orderBenefits(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    22,
                    mapOf(
                        Menu("양송이수프", 6000) to 1,
                        Menu("바비큐립", 54000) to 1
                    ),
                    false,
                    5123
                ),
                Arguments.of(
                    10,
                    mapOf(
                        Menu("시저샐러드", 8000) to 2,
                        Menu("해산물파스타", 35000) to 5,
                        Menu("초코케이크", 15000) to 2,
                    ),
                    true,
                    31946
                )
            )
        }
    }

}