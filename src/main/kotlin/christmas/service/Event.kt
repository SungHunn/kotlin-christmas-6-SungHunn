package christmas.service

import christmas.model.Menu
import christmas.model.MenuCategory
import christmas.util.Constants.FREE_GIFT

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

    fun freeGiftPrice() : Int {
        var price = 0
        MenuCategory.DRINK.menuList.forEach { drink ->
            if (drink.name == FREE_GIFT)
                price = drink.price
        }
        return price
    }

    fun specialDayEvent() : Int = 1000

    fun checkBeforeChristmas(date: Int): Boolean = date <= 25
    fun checkWeekday(date: Int): Boolean = (date % 7 != 1 && date % 7 != 2)
    fun checkWeekend(date: Int): Boolean = (date % 7 == 1 || date % 7 == 2)
    fun checkSpecialDay(date: Int) : Boolean = (date % 7 == 3 || date == 25)

}