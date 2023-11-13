package christmas.view

import christmas.model.Menu
import christmas.service.Calculator
import christmas.util.Constants.AMOUNT_BEFORE_DISCOUNT
import christmas.util.Constants.DECEMBER
import christmas.util.Constants.GREETING
import christmas.util.Constants.ORDER_MENU
import christmas.util.Constants.PREVIEW_EVENT_PLANNER

class OutputView {

    fun printStart() {
        println(GREETING)
    }

    fun printPreviewEvent(date : Int){
        println(DECEMBER + " ${date}일에 "+ PREVIEW_EVENT_PLANNER)
    }

    fun printOrder(order : Map<Menu, Int>) {
        println("\n"+ ORDER_MENU)

        order.forEach {
            println("${it.key.name} ${it.value}개")
        }
    }

    fun printAmountBeforeDiscount(order : Map<Menu, Int>) : Int{
        println("\n"+ AMOUNT_BEFORE_DISCOUNT)

        val amount = Calculator().calculateBeforeDiscount(order)
        println(formatAmount(amount)+"원")

        return amount
    }

    fun formatAmount(amount : Int) : String{
        return "%,d".format(amount)
    }


}