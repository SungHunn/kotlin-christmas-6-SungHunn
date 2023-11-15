package christmas.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class EventTest {

    private lateinit var event : Event

    @BeforeEach
    fun setUp() {
        event = Event()
    }

    @ParameterizedTest
    @MethodSource("freeGift")
    fun `증정 메뉴 여부에 대한 테스트`(amount: Int, check: Boolean) {
        val checkForFreeGift = event.freeGiftEvent(amount)

        assertEquals(check, checkForFreeGift)
    }

    @Test
    fun checkBeforeChristmas() {
    }

    @Test
    fun checkWeekday() {
    }

    @Test
    fun checkWeekend() {
    }

    @Test
    fun checkSpecialDay() {
    }

    companion object {

        @JvmStatic
        fun freeGift(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(130000, true),
                Arguments.of(65000, false),
                Arguments.of(210000, true)
            )
        }

    }
}