package christmas.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class EventTest {

    private lateinit var event: Event

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

    @ParameterizedTest
    @MethodSource("christmasDday")
    fun `크리스마스 이벤트 기간 여부에 대한 테스트`(date: Int, check: Boolean) {
        val checkChristmasDay = event.checkBeforeChristmas(date)

        assertEquals(check, checkChristmasDay)
    }

    @ParameterizedTest
    @MethodSource("includeWeekDay")
    fun `평일 이벤트에 해당되는지에 대한 테스트`(date: Int, check: Boolean) {
        val checkWeekDay = event.checkWeekday(date)

        assertEquals(check, checkWeekDay)
    }

    @ParameterizedTest
    @MethodSource("includeWeekEnd")
    fun `주말 이벤트에 해당되는지에 대한 테스트`(date: Int, check: Boolean) {
        val checkWeekEnd = event.checkWeekend(date)

        assertEquals(check, checkWeekEnd)
    }

    @ParameterizedTest
    @MethodSource("includeSpecialDay")
    fun `특별 할인 이벤트에 해당되는지에 대한 테스트`(date: Int, check: Boolean) {
        val checkSpecialDay = event.checkSpecialDay(date)

        assertEquals(check, checkSpecialDay)
    }

    @ParameterizedTest
    @MethodSource("giveBadge")
    fun `이벤트 배지에 대한 테스트`(benefit: Int, expectedBadge: String) {
        val actualBadge = event.badgeEvent(benefit)

        assertEquals(expectedBadge, actualBadge)
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

        @JvmStatic
        fun christmasDday(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(7, true),
                Arguments.of(16, true),
                Arguments.of(29, false)
            )
        }

        @JvmStatic
        fun includeWeekDay(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(6, true),
                Arguments.of(11, true),
                Arguments.of(8, false)
            )
        }

        @JvmStatic
        fun includeWeekEnd(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(8, true),
                Arguments.of(16, true),
                Arguments.of(21, false)
            )
        }

        @JvmStatic
        fun includeSpecialDay(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(3, true),
                Arguments.of(17, true),
                Arguments.of(28, false)
            )
        }

        @JvmStatic
        fun giveBadge(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(28000, "산타"),
                Arguments.of(16000, "트리"),
                Arguments.of(9000, "별"),
                Arguments.of(2000, "없음")
            )
        }

    }
}