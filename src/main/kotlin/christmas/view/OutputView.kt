package christmas.view

import christmas.model.Menu
import christmas.util.Constants.DECEMBER
import christmas.util.Constants.GREETING
import christmas.util.Constants.ORDER_MENU
import christmas.util.Constants.PREVIEW_EVENT_PLANNER
import christmas.util.Constants.REQUEST_VISIT_DATE
import java.awt.SystemColor.menu

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


}