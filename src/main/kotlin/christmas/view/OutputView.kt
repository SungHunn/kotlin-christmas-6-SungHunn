package christmas.view

import christmas.model.Menu
import christmas.model.MenuCategory
import christmas.service.Calculator
import christmas.service.Event
import christmas.util.Constants.AMOUNT_BEFORE_DISCOUNT
import christmas.util.Constants.BENEFIT_DETAIL
import christmas.util.Constants.CHRISTMAS_DDAY_DISCOUNT
import christmas.util.Constants.DATE
import christmas.util.Constants.DECEMBER
import christmas.util.Constants.EVENT_BADGE
import christmas.util.Constants.EXPECTED_AMOUNT
import christmas.util.Constants.FREEGIFT_EVENT
import christmas.util.Constants.FREE_GIFT
import christmas.util.Constants.FREE_GIFT_PHRASES
import christmas.util.Constants.GREETING
import christmas.util.Constants.NOTHING
import christmas.util.Constants.ORDER_MENU
import christmas.util.Constants.PREVIEW_EVENT_PLANNER
import christmas.util.Constants.SPECIAL_DISCOUNT
import christmas.util.Constants.SUM_BENEFIT
import christmas.util.Constants.WEEKDAY_DISCOUNT
import christmas.util.Constants.WEEKEND_DISCOUNT

class OutputView {

    private val event = Event()

    fun printStart() {
        println(GREETING)
    }

    fun printPreviewEvent(date: Int) {
        println(DECEMBER + " ${date}"+ DATE + PREVIEW_EVENT_PLANNER)
    }

    fun printOrder(order: Map<Menu, Int>) {
        println("\n" + ORDER_MENU)

        order.forEach { menu ->
            println("${menu.key.getName()} ${menu.value}개")
        }
    }

    fun printNothing() {
        println(NOTHING)
    }

    fun printAmountBeforeDiscount(amount: Int): Int {
        println("\n" + AMOUNT_BEFORE_DISCOUNT)

        println(formatAmount(amount) + "원")

        return amount
    }

    fun printFreeGift() {
        println("$FREE_GIFT 1개")
    }

    fun printBenefitDetail(date: Int, order: Map<Menu, Int>, freeGift: Boolean) {
        println("\n" + BENEFIT_DETAIL)

        if (event.checkBeforeChristmas(date))
            printDetailEventBenefit(CHRISTMAS_DDAY_DISCOUNT, event.christmasDdayEvent(date))

        if (event.checkWeekday(date) && event.weekdayEvent(order) != 0)
            printDetailEventBenefit(WEEKDAY_DISCOUNT, event.weekdayEvent(order))

        if (event.checkWeekend(date) && event.weekendEvent(order) != 0)
            printDetailEventBenefit(WEEKEND_DISCOUNT, event.weekendEvent(order))

        if (event.checkSpecialDay(date))
            printDetailEventBenefit(SPECIAL_DISCOUNT, event.specialDayEvent())

        if (freeGift) printDetailEventBenefit(FREEGIFT_EVENT, event.freeGiftPrice())

        notAllConditions(date, freeGift)
        specialCase(date, order, freeGift)
    }

    private fun notAllConditions(date: Int, freeGift: Boolean){
        if (!event.checkBeforeChristmas(date) &&
            !event.checkWeekday(date) &&
            !event.checkWeekend(date) &&
            !freeGift &&
            !event.checkSpecialDay(date)
        )
            println(NOTHING)
    }

    private fun specialCase(date: Int, order: Map<Menu, Int>, freeGift: Boolean){
        if (!event.checkBeforeChristmas(date) &&
            ((event.checkWeekday(date) && event.weekdayEvent(order) == 0) ||
            (event.checkWeekend(date) && event.weekendEvent(order) == 0)) &&
            !freeGift &&
            !event.checkSpecialDay(date)
        )
            println(NOTHING)
    }

    private fun printDetailEventBenefit(event : String, amount : Int) {
        println(event + "-" + formatAmount(amount) + "원")
    }

    fun printSumBenefits(benefit: Int): Int {
        println("\n" + SUM_BENEFIT)

        if (benefit == 0) {
            println("0원")
            return 0
        }
        println("-" + formatAmount(benefit) + "원")
        return benefit
    }

    fun printDiscountedTotalAmount(price: Int, benefit: Int, freeGift: Boolean) {
        println("\n" + EXPECTED_AMOUNT)
        println(formatAmount(Calculator(event).calculateTotalPrice(price, benefit, freeGift)) + "원")
    }

    fun printEventBadge(benefit: Int) {
        println("\n" + EVENT_BADGE)
        println(event.badgeEvent(benefit))
    }

    private fun formatAmount(amount: Int): String {
        return "%,d".format(amount)
    }

}