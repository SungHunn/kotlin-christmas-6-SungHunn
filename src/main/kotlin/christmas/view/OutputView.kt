package christmas.view

import christmas.model.Menu
import christmas.model.MenuCategory
import christmas.service.Calculator
import christmas.service.Event
import christmas.util.Constants.AMOUNT_BEFORE_DISCOUNT
import christmas.util.Constants.BENEFIT_DETAIL
import christmas.util.Constants.CHRISTMAS_DDAY_DISCOUNT
import christmas.util.Constants.DECEMBER
import christmas.util.Constants.FREEGIFT_EVENT
import christmas.util.Constants.FREE_GIFT
import christmas.util.Constants.FREE_GIFT_PHRASES
import christmas.util.Constants.GREETING
import christmas.util.Constants.NOTHING
import christmas.util.Constants.ORDER_MENU
import christmas.util.Constants.PREVIEW_EVENT_PLANNER
import christmas.util.Constants.SPECIAL_DISCOUNT
import christmas.util.Constants.WEEKDAY_DISCOUNT
import christmas.util.Constants.WEEKEND_DISCOUNT

class OutputView {

    private val event = Event()

    fun printStart() {
        println(GREETING)
    }

    fun printPreviewEvent(date: Int) {
        println(DECEMBER + " ${date}일에 " + PREVIEW_EVENT_PLANNER)
    }

    fun printOrder(order: Map<Menu, Int>) {
        println("\n" + ORDER_MENU)

        order.forEach {
            println("${it.key.name} ${it.value}개")
        }
    }

    fun printAmountBeforeDiscount(order: Map<Menu, Int>): Int {
        println("\n" + AMOUNT_BEFORE_DISCOUNT)

        val amount = Calculator().calculateBeforeDiscount(order)
        println(formatAmount(amount) + "원")

        return amount
    }

    fun printFreeGift(amount: Int): Boolean {
        println("\n" + FREE_GIFT_PHRASES)

        if (event.freeGiftEvent(amount)) {
            println("$FREE_GIFT 1개")
            return true
        }

        println(NOTHING)
        return false
    }

    fun printBenefitDetail(date: Int, order: Map<Menu, Int>, freeGift: Boolean) {
        println("\n" + BENEFIT_DETAIL)

        if (event.checkBeforeChristmas(date))
            println(CHRISTMAS_DDAY_DISCOUNT + "-" + formatAmount(event.christmasDdayEvent(date)) + "원")

        if (event.checkWeekday(date))
            println(WEEKDAY_DISCOUNT + "-" + formatAmount(event.weekdayEvent(order)) + "원")

        if (event.checkWeekend(date))
            println(WEEKEND_DISCOUNT + "-" + formatAmount(event.weekendEvent(order)) + "원")

        if (event.checkSpecialDay(date))
            println(SPECIAL_DISCOUNT + "-" + formatAmount(event.specialDayEvent()) + "원")

        if (freeGift) println(FREEGIFT_EVENT + "-" + formatAmount(event.freeGiftPrice()) + "원")

        if (!event.checkBeforeChristmas(date) && !event.checkWeekday(date) && !event.checkWeekend(date)
            && !freeGift && !event.checkSpecialDay(date)
        )
            println(NOTHING)
    }

    private fun formatAmount(amount: Int): String {
        return "%,d".format(amount)
    }


}