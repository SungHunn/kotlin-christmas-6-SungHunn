package christmas

import christmas.controller.EventPlannerController
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    //TODO("프로그램 구현")

    val eventPlannerController = EventPlannerController(InputView(), OutputView())
    eventPlannerController.presentExample()
}
