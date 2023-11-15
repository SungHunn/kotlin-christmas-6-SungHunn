package christmas.util

import christmas.model.MenuCategory
import java.awt.SystemColor.menu


object Validation {

    fun validateVisitDate(date: String) {

        require(date.toIntOrNull() != null) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        require(date.toInt() in 1..31) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
    }

    fun validateOrderMenu(order: String) {

        val orders = order.split(",")

        require(validateCorrectFormat(orders)) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
        require(validateDuplicateOrder(orders)) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
        require(validateDrink(orders)) { "[ERROR] 음료만 주문 시, 주문할 수 없습니다." }
        require(validateOrderDishes(orders)) { "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다." }
    }

    fun validateCorrectFormat(orders: List<String>): Boolean {

        orders.forEach { menu ->
            val menuFormat = menu.split("-")

            if (!menu.split("").contains("-") ||
                MenuCategory.findMenu(menuFormat[0]) == null ||
                menuFormat[1].toIntOrNull() == null ||
                menuFormat[1].toInt() < 1
            ) return false
        }
        return true
    }

    fun validateDrink(orders: List<String>): Boolean {
        return !orders.all { orderMine ->
            val menuName = orderMine.split("-")[0]
            MenuCategory.checkOnlyDrink(menuName)
        }
    }

    fun validateOrderDishes(orders: List<String>): Boolean {
        var dishes = 0
        orders.forEach { menu ->
            dishes += menu.split("-")[1].toInt() }

        return dishes <= 20
    }

    fun validateDuplicateOrder(orders: List<String>): Boolean {
        val orderMenuName = mutableListOf<String>()
        orders.forEach { menuName ->
            orderMenuName.add(menuName.split("-")[0])
        }

        return orderMenuName.size == orderMenuName.distinct().size
    }
}