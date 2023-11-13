package christmas.service

import christmas.model.Menu

class Calculator {

    fun calculateBeforeDiscount(order : Map<Menu, Int>) : Int {
        var amount = 0
        order.forEach { orderMenu ->
            amount += orderMenu.key.price * orderMenu.value
        }

        return amount
    }
}