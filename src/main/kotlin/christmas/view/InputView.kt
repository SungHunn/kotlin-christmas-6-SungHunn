package christmas.view

import christmas.util.Constants.REQUEST_VISIT_DATE
import camp.nextstep.edu.missionutils.Console
import christmas.util.Constants.TAKE_ORDER
import christmas.util.Validation.validateOrderMenu
import christmas.util.Validation.validateVisitDate

class InputView {

    fun readVisitDate(): Int {
        println(REQUEST_VISIT_DATE)

        return checkVisitDate()
    }

    private fun checkVisitDate(): Int {

        return try {
            val input = Console.readLine()
            validateVisitDate(input)
            input.toInt()
        } catch (e: IllegalArgumentException) {
            println(e)
            checkVisitDate()
        }
    }

    fun readOrder() : List<String>{
        println(TAKE_ORDER)

        return checkOrder()
    }

    private fun checkOrder() : List<String>{
        return try {
            val order = Console.readLine()
            validateOrderMenu(order)
            order.split(",")
        } catch (e: IllegalArgumentException) {
            println(e)
            checkOrder()
        }

    }
}