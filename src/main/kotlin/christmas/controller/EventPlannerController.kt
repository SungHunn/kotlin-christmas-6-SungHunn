package christmas.controller

import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val input: InputView,
    private val output: OutputView
) {

    fun present() {
        output.printStart()
        input.readVisitDate()
        input.readOrder()

    }
}