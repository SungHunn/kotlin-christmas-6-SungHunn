package christmas.controller

import christmas.model.Menu
import christmas.model.MenuCategory
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val input: InputView,
    private val output: OutputView
) {

    fun present() {
        output.printStart()
        val date = input.readVisitDate()
        val order = MenuCategory.getMenuList(input.readOrder())

        output.printPreviewEvent(date)
        output.printOrder(order)


    }


}