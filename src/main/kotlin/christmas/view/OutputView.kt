package christmas.view

import christmas.model.Menu
import christmas.service.Calculator
import christmas.service.Event
import christmas.util.Constants.AMOUNT_BEFORE_DISCOUNT
import christmas.util.Constants.DECEMBER
import christmas.util.Constants.FREE_GIFT
import christmas.util.Constants.FREE_GIFT_PHRASES
import christmas.util.Constants.GREETING
import christmas.util.Constants.NOTHING
import christmas.util.Constants.ORDER_MENU
import christmas.util.Constants.PREVIEW_EVENT_PLANNER

class OutputView {

    val event = Event()

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

        if (event.checkFreeGift(amount)) {
            println(FREE_GIFT)
            return true
        }

        println(NOTHING)
        return false
    }

    private fun formatAmount(amount: Int): String {
        return "%,d".format(amount)
    }


}