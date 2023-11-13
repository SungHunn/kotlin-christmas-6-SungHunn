package christmas.service

import christmas.model.Menu

class Calculator(val event : Event) {

    fun calculateBeforeDiscount(order : Map<Menu, Int>) : Int {
        var amount = 0
        order.forEach { orderMenu ->
            amount += orderMenu.key.price * orderMenu.value
        }

        return amount
    }

    fun calculateBenefitPrice(date: Int, order: Map<Menu, Int>, freeGift: Boolean) : Int {
        var benefit = 0
        if (event.checkBeforeChristmas(date)) benefit += event.christmasDdayEvent(date)
        if (event.checkWeekday(date)) benefit += event.weekdayEvent(order)
        if (event.checkWeekend(date)) benefit += event.weekendEvent(order)
        if (event.checkSpecialDay(date)) benefit += event.specialDayEvent()
        if (freeGift) benefit += event.freeGiftPrice()

        return benefit
    }

    fun calculateTotalPrice(price : Int, benefit : Int, freeGift: Boolean) : Int {
        if (freeGift) return price - (benefit - 25000)
        return price - benefit
    }
}