package christmas.service

import christmas.model.Menu
import christmas.model.MenuCategory
import christmas.util.Constants.NOTHING
import christmas.util.Constants.SANTA
import christmas.util.Constants.STAR
import christmas.util.Constants.TREE

class Event {

    fun freeGiftEvent(amount: Int): Boolean {
        return amount >= 120000
    }

    fun christmasDdayEvent(date: Int): Int {
        val discount = 1000 + (date - 1) * 100
        return discount
    }

    fun weekdayEvent(orderMenu: Map<Menu, Int>): Int {
        return MenuCategory.findCategory(MenuCategory.DESSERT, orderMenu) * 2023
    }

    fun weekendEvent(orderMenu: Map<Menu, Int>): Int {
        return MenuCategory.findCategory(MenuCategory.MAIN, orderMenu) * 2023
    }

    fun freeGiftPrice(): Int {
        return MenuCategory.findFreeGiftPrice()
    }

    fun specialDayEvent(): Int = 1000

    fun badgeEvent(benefit: Int): String {
        return when {
            benefit >= 20000 -> SANTA
            benefit >= 10000 -> TREE
            benefit >= 5000 -> STAR
            else -> NOTHING
        }
    }

    fun checkBeforeChristmas(date: Int): Boolean = date <= 25
    fun checkWeekday(date: Int): Boolean = (date % 7 != 1 && date % 7 != 2)
    fun checkWeekend(date: Int): Boolean = (date % 7 == 1 || date % 7 == 2)
    fun checkSpecialDay(date: Int): Boolean = (date % 7 == 3 || date == 25)

}